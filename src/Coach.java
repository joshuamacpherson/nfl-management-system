import java.io.Serial;
import java.io.Serializable;

public class Coach implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String coachID;
    private String name;
    private String teamName;
    private int yearsOfExperience;

    public Coach(String coachID, String name, String teamName, int yearsOfExperience) {
        this.coachID = coachID;
        this.name = name;
        this.teamName = teamName;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getCoachID() {
        return coachID;
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    @Override
    public String toString() {
        return String.format("%-4s %-20s %-9s %-3s", coachID, name, teamName, yearsOfExperience);
    }
}
