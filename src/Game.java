/**
 * Student Name: Joshua MacPherson
 * Student ID: 041166405
 * Course: CST8132 - Object-Oriented Programming
 * Professor: James Mwangi
 * Assignment: OOP Assignment 2
 * Due Date: 2025-3-30
 * Class Description: This class represents the Game object in the NFL management system.
 * It stores the teams, date, location, and score of the game.
 */
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

    @Override
    public String toString() {
        return String.format("%-9s %-9s %-12s %-15s %-6s", team1, team2, date, location, score);
    }
}
