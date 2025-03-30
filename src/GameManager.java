import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameManager{

    ArrayList<Game> games = new ArrayList<>();

    public void displayGames() {
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("%-9s %-9s %-12s %-15s %-6s%n", "Team 1", "Team 2", "Date", "Location", "Score");
        System.out.println("-------------------------------------------------------------------");
        for (Game game : games) {
            System.out.println(game);
        }
    }

    public void loadGamesFromFile(String gameFileName) {
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
