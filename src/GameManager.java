/**
 * Student Name: Joshua MacPherson
 * Student ID: 041166405
 * Course: CST8132 - Object-Oriented Programming
 * Professor: James Mwangi
 * Assignment: OOP Assignment 2
 * Due Date: 2025-3-30
 * Class Description: This class manages the games in the NFL management system. It allows the loading of games from a file,
 * and the display of these games.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameManager{
    ArrayList<Game> games = new ArrayList<>(); // stores all game objects

    public void displayGames() { // displays all games in the games list
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%-9s %-9s %-12s %-15s %-6s%n", "Team 1", "Team 2", "Date", "Location", "Score");
        System.out.println("-------------------------------------------------------------------");
        for (Game game : games) {
            System.out.println(game);
        }
    }

    /**
     * loads data from the file, and creates the games
     * @param gameFileName the name of the file containing game data
     */
    public void loadGamesFromFile(String gameFileName) { // loads games from given file
        try (BufferedReader br = new BufferedReader(new FileReader(gameFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] gameData = line.split(",");
                Game game = new Game(gameData[0], gameData[1], gameData[2], gameData[3], gameData[4]);
                games.add(game);
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }
}
