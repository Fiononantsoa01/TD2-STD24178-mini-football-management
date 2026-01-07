package Player;

import Continent.ContinentEnum;

import java.util.List;
import java.util.Objects;

public class Team {
    private int id;
    private String name;
    private ContinentEnum continentEnum;
    private List<Player> players;

    public Team(int id, String name, ContinentEnum continentEnum, List<Player> players) {
        this.id = id;
        this.name = name;
        this.continentEnum = continentEnum;
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContinentEnum getContinent() {
        return continentEnum;
    }

    public void setContinent(ContinentEnum continentEnum) {
        this.continentEnum = continentEnum;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    /*pour eviter le nulpointerException au cas ou players list est null*/
    public Integer getPlayersCount() {
        return players != null ? players.size() : 0;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id && Objects.equals(name, team.name) && continentEnum == team.continentEnum && Objects.equals(players, team.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, continentEnum, players);
    }

    @Override
    public String toString() {
        return "Player.Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", continent=" + continentEnum +
                ", players=" + players +
                '}';
    }
}
