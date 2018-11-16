package pl.matsyposz.ox.io;

import pl.matsyposz.ox.GameMap;

import java.io.PrintStream;
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

    public void showMap() {

        for (int column = 0; column < gameMap.width; column++) {
            printRow(column);
        }
    }

    private void printRow(int column) {

        for (int row = 0; row < gameMap.height; row++) {
            printStream.print(" " + gameMap.check(column, row) + " ");
        }
        printStream.println();
    }
}