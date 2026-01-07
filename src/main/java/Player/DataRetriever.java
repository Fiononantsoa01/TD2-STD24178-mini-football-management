package Player;

import Continent.ContinentEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {
    public Team findTeamById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        DBConnection db = new DBConnection();
        Connection conn= null;
        Team team = null;
        String sqlTeam = "SELECT id,name, continent FROM team WHERE id = ?";
        String sqlPlayers = "SELECT id,age, name, age, position, id_team FROM player WHERE id_team = ?";
/*1- find the te  te  team*/
        try{
            conn = db.getDBConnection();
            try(
                    PreparedStatement psTeam=conn.prepareStatement(sqlTeam)) {
                psTeam.setInt(1,id);
                try (
                        ResultSet rsTeam= psTeam.executeQuery()) {
                    if (rsTeam.next()) {
                        team = new Team(
                                rsTeam.getInt("id"),
                                rsTeam.getString("name"),
                                ContinentEnum.valueOf(rsTeam.getString("continent")),
                                new ArrayList<>()
                        );
                    } else {
                        return null;
                    }
                }
            }
            /*to find all the player in the team*/
            try (PreparedStatement psPlayers = conn.prepareStatement(sqlPlayers)) {
                psPlayers.setInt(1, id);
                try (ResultSet rsPlayers = psPlayers.executeQuery()) {
                    while (rsPlayers.next()) {
                        Player player = new Player(
                                rsPlayers.getInt("id"),
                                rsPlayers.getInt("age"),
                                rsPlayers.getString("name"),
                                PlayerPositionEnum.valueOf(rsPlayers.getString("position")),
                                team
                        );
                        team.getPlayers().add(player);
                    }
                }
            }
    }
        catch (SQLException e) {
            throw new RuntimeException("team whiteh id" + id +"couldn't find", e);
        } finally {
            db.closeConnection(conn);
        }
        return team;
    }
    public List<Player> findPlayers(int page , int size){
        DBConnection db = new DBConnection();
        Connection conn= null;
        try {
            conn = db.getDBConnection();
            if (page < 1) page = 1;
            if (size < 1) size = 20;
            if (size > 60) size = 30;

            List<Player> playerList = new ArrayList<>();
            int offset = (page - 1) * size;
            String sqlPlayerList = "SELECT id, name, age, position, id_team FROM player ORDER BY id LIMIT ? OFFSET ?";
            try (PreparedStatement prs = db.getDBConnection().prepareStatement(sqlPlayerList)) {
                prs.setInt(1, size);
                prs.setInt(2, offset);
                try (ResultSet rs = prs.executeQuery()) {
                    while (rs.next()) {
                        Player player = new Player(
                                rs.getInt("id"),
                                rs.getInt("age"),
                                rs.getString("name"),
                                PlayerPositionEnum.valueOf(rs.getString("position")),
                                null  // team = null pour éviter référence circulaire infinie
                        );
                        playerList.add(player);
                    }
                }
            }

            return playerList;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération paginée des joueurs (page=" + page + ", size=" + size + ")", e);
        } finally {
            db.closeConnection(conn);
        }
    }
}

