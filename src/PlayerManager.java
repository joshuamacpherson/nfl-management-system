import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerManager {

    ArrayList<Player> players = new ArrayList<>();

    public void addPlayer(String playerID, String name, int age, String teamName, String position, TeamManager teamManager) {

            Player player = new Player(playerID, name, age, teamName, position);
            players.add(player);
            Team team = teamManager.findTeam(teamName);
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
