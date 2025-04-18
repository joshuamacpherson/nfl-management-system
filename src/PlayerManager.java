/**
 * Student Name: Joshua MacPherson
 * Student ID: 041166405
 * Course: CST8132 - Object-Oriented Programming
 * Professor: James Mwangi
 * Assignment: OOP Assignment 2
 * Due Date: 2025-3-30
 * Class Description: This class manages the players in the NFL management system. It allows the adding of players and
 * displaying of players. It also loads the players from a file.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerManager {
    ArrayList<Player> players = new ArrayList<>(); // stores all player objects

    /**
     * adds a player to the players list and the correct team
     * @param playerID player's ID
     * @param name player's name
     * @param age player's age
     * @param teamName team name the player belongs to
     * @param position player's position for the team
     * @param teamManager the team manager object to find the team and assign the player correctly
     */
    public void addPlayer(String playerID, String name, int age, String teamName, String position, TeamManager teamManager) {
        // adds player to the players list, and the correct team
            Player player = new Player(playerID, name, age, teamName, position);
            players.add(player);
            Team team = teamManager.findTeam(teamName); // instantiates team object
            team.addPlayer(player); // adds player to that team
    }

    /**
     * loads players from a file and adds them to the players list and the correct team
     * @param playerFileName the name of the file containing player data
     * @param teamManager the team manager object to find the team and assign the player correctly
     */
    public void loadPlayersFromFile(String playerFileName, TeamManager teamManager) {
        try (BufferedReader br = new BufferedReader(new FileReader(playerFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] playerData = line.split(",");
                Player player = new Player(playerData[0].trim(), playerData[1].trim(), Integer.parseInt(playerData[2].trim()), playerData[3].trim(), playerData[4].trim());
                players.add(player);
                Team team = teamManager.findTeam(playerData[3]);
                if (team != null) {
                    team.addPlayer(player);
                } else {
                    System.out.println("Team not found for player " + player.getName());
                }
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public void displayPlayers() {
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%-4s %-17s %-4s %-9s %-20s%n", "ID", "Player", "Age", "Team", "Position");
        System.out.println("-------------------------------------------------------------------");
        for (Player player : players) {
            System.out.println(player);
        }
    }
}
