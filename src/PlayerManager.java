import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayerManager {
    ArrayList<Player> players = new ArrayList<>();

    public void addPlayer(Scanner sc, TeamManager teamManager) {
            System.out.print("Enter player ID: ");
            String playerID = sc.nextLine().trim();

            System.out.print("Enter player name: ");
            String playerName = sc.nextLine().trim();

            // handling errors for player age if user enters a string instead of a number
            int playerAge;
            while (true) {
                System.out.print("Enter player age: ");
                try {
                    playerAge = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number.");
                    sc.nextLine();
                }
            }

            Team team = null;
            while (team == null) {
                System.out.print("Enter team name: ");
                String teamName = sc.nextLine().trim();
                team = teamManager.findTeam(teamName);
                if (team == null) {
                    System.out.println("Team does not exist");
                }
            }

            System.out.print("Enter player position: ");
            String playerPosition = sc.nextLine().trim();

            Player player = new Player(playerID, playerName, playerAge, team.getTeamName(), playerPosition);
            players.add(player);
            team.addPlayer(player);
    }

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
