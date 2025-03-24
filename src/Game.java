import java.io.Serial;
import java.io.Serializable;

public class Game implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String team1;
    private String team2;
    private String date;
    private String location;
    private String score;

    public Game(String team1, String team2, String date, String location, String score) {
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.location = location;
        this.score = score;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("%-9s %-9s %-12s %-15s %-6s", team1, team2, date, location, score);
    }
}
