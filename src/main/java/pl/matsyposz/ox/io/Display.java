package pl.matsyposz.ox.io;

import pl.matsyposz.ox.GameMap;

import java.io.PrintStream;

public class Display {

    private PrintStream printStream;
    private GameMap gameMap;

    public Display(PrintStream printStream, GameMap gameMap) {
        this.printStream = printStream;
        this.gameMap = gameMap;
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