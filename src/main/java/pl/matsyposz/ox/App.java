package pl.matsyposz.ox;

import pl.matsyposz.ox.io.UserInput;

import java.util.ArrayList;

public class App {
    private static GameController gameController;

    private static void gameInit() {
        GameMap gameMap = new GameMap(3, 3);
        UserInput userInput = new UserInput(System.in);
        WinConditions winConditions = new WinConditions(gameMap, userInput);
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player('O', gameMap));
        players.add(new Player('X', gameMap));
        gameController = new GameController(gameMap, winConditions, players, userInput);
    }

    public static void main(String[] args) {

        gameInit();
        gameController.start();
    }
}