/**
 * Student Name: Joshua MacPherson
 * Student ID: 041166405
 * Course: CST8132 - Object-Oriented Programming
 * Professor: James Mwangi
 * Assignment: OOP Assignment 2
 * Due Date: 2025-3-30
 * Class Description: This class represents the Player object in the NFL management system. It stores the player's ID, name, age, team name,
 * and position. It also implements the Serializable interface to allow for serialization of the object.
 */
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

    public int getAge() {
        return age;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%-4s %-17s %-4s %-9s %-20s", playerID, name, age, teamName, position);
    }
}
