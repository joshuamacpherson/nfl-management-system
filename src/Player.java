import java.io.Serial;
import java.io.Serializable;

public class Player implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String playerID;
    private String name;
    private int age;
    private String teamName;
    private String position;

    public Player(String playerID, String name, int age, String teamName, String position) {
        this.playerID = playerID;
        this.name = name;
        this.age = age;
        this.teamName = teamName;
        this.position = position;
    }

    public String getPlayerID() {
        return playerID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.format("%-4s %-17s %-4s %-9s %-20s", playerID, name, age, teamName, position);
    }
}
