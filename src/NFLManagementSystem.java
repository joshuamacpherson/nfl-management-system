import java.util.InputMismatchException;
import java.util.Scanner;

public class NFLManagementSystem {
    public static void main(String[] args) {
        /*
        Loading all the data from the CSV files into memory
         */
        TeamManager teamManager = new TeamManager();
        teamManager.loadTeamsFromFile("couches.csv", "teams.csv");
        PlayerManager playerManager = new PlayerManager();
        playerManager.loadPlayersFromFile("players.csv", teamManager);
        GameManager gameManager = new GameManager();
        gameManager.loadGamesFromFile("games.csv");

        Scanner sc = new Scanner(System.in);

        int userChoice = 0;
        do {
            NFLManagementSystem.showMenu();
            try {
                userChoice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("-------------------------------------------------------------------");
                System.out.println("\n************************  INVALID INPUT  **************************\n");
                sc.nextLine();
            }

            switch (userChoice) {
                case 1:
                    teamManager.displayTeams();
                    break;
                case 2:
                    playerManager.displayPlayers();
                    break;
                case 3:
                    gameManager.displayGames();
                    break;
                case 4:
                    teamManager.addTeam(sc);
                    break;
                case 5:
                    playerManager.addPlayer(sc, teamManager);
                    break;
                case 6:
                    System.out.print("Enter team name: ");
                    String teamName = sc.nextLine().trim();
                    Team team = teamManager.findTeam(teamName);
                    if (team != null) {
                        System.out.println("-------------------------------------------------------------------");
                        System.out.println("Found team: " + team.getTeamName() + "\nCity: " + team.getCity() + "\nCoach: "
                        + team.getCoach().getName() + " (" + team.getCoach().getYearsOfExperience() + " years of experience)");
                    } else {
                        System.out.println("Team not found.");
                    }
                    break;
                case 7:
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("\n*************  Exiting program by Joshua MacPherson  **************\n");
                    System.out.println("-------------------------------------------------------------------");
                    break;
                default:
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("\n************************  INVALID INPUT  **************************\n");
                    break;
            }
        } while (userChoice != 7);
        sc.close();
        System.out.println("The 'Serializable' interface allows an object to be serialized and deserialized, " +
                "\nthe role of the UID number would be to only allow compatible versions of the object to be \nserialized and deserialized, " +
                "and whoever is managing them should increment the \nUID every time a new version is created that is not compatible with an older version.");
    }

    private static void showMenu() {
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