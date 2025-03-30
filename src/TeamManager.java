/**
 * Student Name: Joshua MacPherson
 * Student ID: 041166405
 * Course: CST8132 - Object-Oriented Programming
 * Professor: James Mwangi
 * Assignment: OOP Assignment 2
 * Due Date: 2025-3-30
 * Class Description: This class manages the teams in the NFL management system. It stores the coaches and the teams,
 * and allows the adding and searching of teams. It also loads the teams from a file.
 */
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TeamManager {
    private ArrayList<Team> teams = new ArrayList<>(); // stores all team objects
    private ArrayList<Coach> coaches = new ArrayList<>(); // stores all coach objects

    public void addTeam(String coachID, String city, String coachName, String teamName, int coachYears) {
        Coach coach = new Coach(coachID, coachName, teamName, coachYears);
        coaches.add(coach);
        teams.add(new Team(teamName, city, coach));
    }

    public void displayTeams() { // displays all teams
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%-9s %-15s %-30s %7s%n", "Team", "City", "Coach", "Players");
        System.out.println("-------------------------------------------------------------------");
        for (Team team : teams) { // prints each team's toString method
            System.out.println(team);
        }
    }

    /**
     * loads data from the files, and creates the teams and coaches
     @param coachFileName the name of the file containing coach data
     @param teamFileName the name of the file containing team data
     */
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

    /**
     * finds a team by its name
     @param teamName the name of the team to find
     @return the team object if found, null otherwise
     */
    public Team findTeam(String teamName) {
        for (Team team : teams) { // iterates through the teams
            if (team.getTeamName().equals(teamName)) { // checks if the team name matches
                return team; // if it does, return the team
            }
        }
        return null; // if no team is found, return null
    }
}
