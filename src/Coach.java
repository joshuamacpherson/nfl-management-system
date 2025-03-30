/**
 * Student Name: Joshua MacPherson
 * Student ID: 041166405
 * Course: CST8132 - Object-Oriented Programming
 * Professor: James Mwangi
 * Assignment: OOP Assignment 2
 * Due Date: 2025-3-30
 * Class Description: This class represents the Coach object in the NFL management system. It stores the coach's ID, name, team name,
 * and years of experience. It also implements the Serializable interface to allow for serialization of the object.
 */
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
