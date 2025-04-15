/**
 * Student Name: Joshua MacPherson
 * Student ID: 041166405
 * Course: CST8132 - Object-Oriented Programming
 * Professor: James Mwangi
 * Assignment: OOP Assignment 2
 * Due Date: 2025-3-30
 * Program Description: This program is an NFL management system that allows the user to manage teams, players, and games.
 * It features an interactive menu system that allows the user to add teams and players, display teams, players and games,
 * and search for specific teams.
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class NFLManagementSystem {
    public static void main(String[] args) {
        /*
        Loading all the data from the CSV files into memory
         */
        TeamManager teamManager = new TeamManager();
        teamManager.loadTeamsFromFile("coaches.csv", "teams.csv");
        PlayerManager playerManager = new PlayerManager();
        playerManager.loadPlayersFromFile("players.csv", teamManager);
        GameManager gameManager = new GameManager();
        gameManager.loadGamesFromFile("games.csv");

        Scanner sc = new Scanner(System.in);

        String userChoice; // loop control variable, stores user input
        /*
        Main body of the program, acts as an interface for the user to interact with the program
         */
        do {
            NFLManagementSystem.showMenu();
            userChoice = sc.nextLine().trim();

            switch (userChoice) {
                case "1": // displays all teams
                    teamManager.displayTeams();
                    break;

                case "2": // displays all players
                    playerManager.displayPlayers();
                    break;

                case "3": // displays all games
                    gameManager.displayGames();
                    break;

                case "4": { // adds a team, case put inside a code block so the variables are not visible outside
                    String teamName;
                    do { // checks if team already exists
                        System.out.print("Enter team name: ");
                        teamName = sc.nextLine().trim();
                        if (teamManager.findTeam(teamName) != null) {
                            System.out.println("Team already exists.");
                            teamName = null;
                        }
                    } while (teamName == null);

                    // gathering more input
                    System.out.print("Enter city: ");
                    String city = sc.nextLine().trim();
                    System.out.print("Enter coach ID: ");
                    String coachID = sc.nextLine().trim();
                    System.out.print("Enter coach name: ");
                    String coachName = sc.nextLine().trim();

                    // handles if numbers are unreasonable, and handles input mismatch exception
                    int coachYears = 0;
                    do {
                        System.out.print("Enter coach's years of experience: ");
                        try {
                            coachYears = sc.nextInt();
                            if (coachYears >= 0 && coachYears <= 100) {
                                sc.nextLine();
                            } else {
                                System.out.println("Please enter a number between 0 and 100.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a number.");
                            sc.nextLine();
                        }
                    } while (coachYears < 0 || coachYears > 100);

                    teamManager.addTeam(coachID, city, coachName, teamName, coachYears);
                    break;
                }

                case "5": // adds a player and assigns them to a team
                    System.out.print("Enter player ID: ");
                    String playerID = sc.nextLine().trim();

                    System.out.print("Enter player name: ");
                    String playerName = sc.nextLine().trim();

                    // handling errors for player age if user enters a string instead of a number
                    int playerAge = 0;
                    do {
                        System.out.print("Enter player age: ");
                        try {
                            playerAge = sc.nextInt();
                            if (playerAge >= 18 && playerAge <= 60) {
                                sc.nextLine();
                            } else {
                                System.out.println("Please enter a number between 18 and 60.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a number.");
                            sc.nextLine();
                        }
                    } while (playerAge < 18 || playerAge > 60);

                    Team team = null;
                    while (team == null) {
                        System.out.print("Enter team name: ");
                        String teamName = sc.nextLine().trim();
                        team = teamManager.findTeam(teamName);
                        if (team == null) {
                            System.out.println("Team does not exist.");
                        }
                    }

                    System.out.print("Enter player position: ");
                    String playerPosition = sc.nextLine().trim();

                    playerManager.addPlayer(playerID, playerName, playerAge, team.getTeamName(), playerPosition, teamManager);
                    break;

                case "6": { // finds a team, case put inside a code block so the variables are not visible outside
                    System.out.print("Enter team name: ");
                    String teamName = sc.nextLine().trim();
                    Team teamFinder = teamManager.findTeam(teamName);
                    if (teamFinder != null) {
                        System.out.println("-------------------------------------------------------------------");
                        System.out.println("Found team: " + teamFinder.getTeamName() + "\nCity: " + teamFinder.getCity() + "\nCoach: "
                                + teamFinder.getCoach().getName() + " (" + teamFinder.getCoach().getYearsOfExperience() + " years of experience)");
                    } else {
                        System.out.println("Team not found.");
                    }
                    break;
                }

                case "7": // exits program
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("\n*************  Exiting program by Joshua MacPherson  **************\n");
                    System.out.println("-------------------------------------------------------------------");
                    break;

                default: // catching any other input
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("\n************************  INVALID INPUT  **************************\n");
                    break;
            }
        } while (!userChoice.equals("7"));

        sc.close(); // close scanner

        /*
        Explanation of the serialVersionUID number, and its purpose
         */
        System.out.println("The 'Serializable' interface allows an object to be serialized and deserialized, " +
                "\nthe role of the UID number would be to only allow compatible versions of the object to be \nserialized and deserialized, " +
                "and whoever is managing them should increment the \nUID every time a new version is created that is not compatible with an older version.");
    }

    private static void showMenu() { // method to display the menu
        System.out.println("-------------------------------------------------------------------");
        System.out.println("                  NFL Operations Management System                 ");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("1. Display Teams");
        System.out.println("2. Display Players");
        System.out.println("3. Display Scheduled Games");
        System.out.println("4. Add Team");
        System.out.println("5. Add Player to Team");
        System.out.println("6. Find Team");
        System.out.println("7. Exit");
        System.out.print("Enter choice: ");
    }
}
