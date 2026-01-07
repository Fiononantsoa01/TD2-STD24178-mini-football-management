package Player;

import java.util.Objects;

public class Player {
    private int id;
    private String name;
    private int age;
    private PlayerPositionEnum playerPosition;
    private Team team;

    public Player(int id, int age,String name, PlayerPositionEnum playerPosition, Team team) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.playerPosition = playerPosition;
        this.team = team;
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
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PlayerPositionEnum getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(PlayerPositionEnum playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    /*pour eviter le nulpointer exception au cas ou team est null*/
    public String getTeamName() {
        return team != null ? team.getName() : "No team";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && age == player.age && Objects.equals(name, player.name) && playerPosition == player.playerPosition && Objects.equals(team, player.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, playerPosition, team);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", playerPosition=" + playerPosition +
                ", team=" + team +
                '}';
    }
}
