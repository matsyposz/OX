package pl.matsyposz.ox;

import java.util.ArrayList;

public class App {

    private static GameMap gameMap;
    private static UserInput userInput;
    private static WinConditions winConditions;
    private static Display display;
    private static GameController gameController;

    private static void gameInit() {
        gameMap = new GameMap(3, 3);
        userInput = new UserInput(System.in);
        winConditions = new WinConditions(gameMap, userInput);
        display = new Display(System.out, gameMap);
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player('O', gameMap));
        players.add(new Player('X', gameMap));
        gameController = new GameController(gameMap, display, winConditions, players, userInput);
    }

    public static void main(String[] args) {

        gameInit();
        gameController.start();
    }
}