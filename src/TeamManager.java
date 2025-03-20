import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TeamManager {

    private ArrayList<Team> teams = new ArrayList<>();
    private ArrayList<Coach> coaches = new ArrayList<>();
    private String teamFileName;
    private String coachFileName;

    public void addPlayer() {
        ;
    }

    public Team getTeamByName(String teamName) {
        for (Team team : teams) {
            if (team.getTeamName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }

    public void addTeam(Scanner sc) {
        System.out.println("Enter team name: ");
        String teamName = sc.nextLine();
        System.out.println("Enter city: ");
        String city = sc.nextLine();
        System.out.println("Enter coach ID: ");
        String coachID = sc.nextLine();
        System.out.println("Enter coach name: ");
        String coachName = sc.nextLine();
        System.out.println("Enter coach's years of experience: ");
        int coachYears = sc.nextInt();
        Coach coach = new Coach(coachID, coachName, teamName, coachYears);
        coaches.add(coach);
        teams.add(new Team(teamName, city, coach));
    }

    public void displayTeams() {
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%-9s %-15s %-30s %7s%n", "Team", "City", "Coach", "Players");
        System.out.println("-------------------------------------------------------------------");
        for (Team team : teams) {
            System.out.println(team);
        }
    }

    public void loadTeamsFromFile(String coachFileName, String teamFileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(coachFileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] coachData = line.split(",");
                Coach coach = new Coach(coachData[0], coachData[1], coachData[2], Integer.parseInt(coachData[3]));
                coaches.add(coach);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(teamFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                for (Coach coach : coaches) {
                    if (coach.getName().equals(data[2])) {
                        Team team = new Team(data[0], data[1], coach);
                        teams.add(team);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }


}
