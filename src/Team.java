import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String teamName;
    private String city;
    private ArrayList<Player> players = new ArrayList<>();
    private Coach coach;
    PlayerManager playerManager = new PlayerManager();

    public Team(String teamName, String city, Coach coach) {
        this.teamName = teamName;
        this.city = city;
        this.coach = coach;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public String getCity() {
        return city;
    }

    public String getTeamName() {
        return teamName;
    }

    public Coach getCoach() {
        return coach;
    }

    @Override
    public String toString() {
        String coachDetails = coach.getName() + " (" + coach.getYearsOfExperience() + " years exp.)";
        return String.format("%-9s %-15s %-30s %-7d", teamName, city, coachDetails, players.size());
    }

}

