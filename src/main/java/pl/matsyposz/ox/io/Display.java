package pl.matsyposz.ox.io;

import pl.matsyposz.ox.GameMap;
import pl.matsyposz.ox.Player;

import java.io.PrintStream;
import java.util.List;
import java.util.ResourceBundle;

public class Display {

    private PrintStream printStream;
    private GameMap gameMap;
    private ResourceBundle resourceBundle;

    public Display(PrintStream printStream, ResourceBundle resourceBundle, GameMap gameMap) {
        this.printStream = printStream;
        this.resourceBundle = resourceBundle;
        this.gameMap = gameMap;
    }

    public void print(String message) {
        printStream.print(resourceBundle.getString(message));
    }

    public void print(String playerName, String message) {
        printStream.print(playerName + resourceBundle.getString(message));
    }

    public void printScores(String playerName, List<Player> players) {
        //stupid list reverse
        int scoreO, scoreX;
        if (players.get(0).getSign().equals('O')) {
            scoreO = players.get(0).getScore();
            scoreX = players.get(1).getScore();
        } else {
            scoreO = players.get(1).getScore();
            scoreX = players.get(0).getScore();
        }

        printStream.println(resourceBundle.getString("matchWin") + playerName + ". "
                + "O: " + scoreO + " "
                + "X: " + scoreX);
    }

    public void showMap() {

        printColumnNum();
        for (int row = 0; row < gameMap.height; row++) {
            printStream.print(row + " ");
            printRow(row);
        }
    }

    private void printRow(int row) {

        for (int column = 0; column < gameMap.height; column++) {
            printStream.print(" " + gameMap.check(column, row) + " ");
        }
        printStream.println();
    }

    private void printColumnNum() {

        printStream.print("   ");
        for (int column = 0; column < gameMap.height; column++) {
            printStream.print(column + "  ");
        }
        printStream.println();
    }
}