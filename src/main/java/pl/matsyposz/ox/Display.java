package pl.matsyposz.ox;

import java.io.PrintStream;

public class Display {

    private PrintStream printStream;
    private GameMap gameMap;

    Display(PrintStream printStream, GameMap gameMap) {
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
            printStream.print(" " + gameMap.check(row, column) + " ");
        }
        printStream.println();
    }
}