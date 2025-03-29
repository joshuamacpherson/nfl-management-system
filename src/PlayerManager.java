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

            int playerAge = PlayerManager.askForInt(sc, "Enter player age: ");

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

    private static int askForInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine();
            }
        }
    }
}
