import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TeamManager {

    private ArrayList<Team> teams = new ArrayList<>();
    private ArrayList<Coach> coaches = new ArrayList<>();

    public void addTeam(Scanner sc) {
        System.out.print("Enter team name: ");
        String teamName = sc.nextLine().trim();
        System.out.print("Enter city: ");
        String city = sc.nextLine().trim();
        System.out.print("Enter coach ID: ");
        String coachID = sc.nextLine().trim();
        System.out.print("Enter coach name: ");
        String coachName = sc.nextLine().trim();

        // handling errors for player age if user enters a string instead of a number
        int coachYears;
        while (true) {
            System.out.print("Enter coach's years of experience: ");
            try {
                coachYears = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number.");
                sc.nextLine();
            }
        }
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
            while ((line = br.readLine()) != null) {
                String[] coachData = line.split(",");
                Coach coach = new Coach(coachData[0].trim(), coachData[1].trim(), coachData[2].trim(), Integer.parseInt(coachData[3].trim()));
                coaches.add(coach);
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(teamFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                for (Coach coach : coaches) {
                    if (coach.getName().equals(data[2].trim())) {
                        Team team = new Team(data[0].trim(), data[1].trim(), coach);
                        teams.add(team);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    public Team findTeam(String teamName) {
        for (Team team : teams) {
            if (team.getTeamName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }
}
