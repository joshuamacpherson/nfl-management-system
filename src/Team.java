/**
 * Student Name: Joshua MacPherson
 * Student ID: 041166405
 * Course: CST8132 - Object-Oriented Programming
 * Professor: James Mwangi
 * Assignment: OOP Assignment 2
 * Due Date: 2025-3-30
 * Class Description: This class represents the Team object in the NFL management system. It stores the team name, city, players, and coach.
 * It also implements the Serializable interface to allow for serialization of the object.
 */
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

