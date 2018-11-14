package pl.matsyposz.ox;

import java.util.Collections;
import java.util.List;

public class GameController {

    private Display display;
    private List<Player> players;
    private UserInput userInput;
    private WinConditions winConditions;
    private GameMap gameMap;
    private Boolean nextMatch;
    private int matchCounter;
    private int moves;

    GameController(GameMap gameMap, Display display, WinConditions winConditions, List<Player> players, UserInput userInput) {
        this.gameMap = gameMap;
        this.display = display;
        this.winConditions = winConditions;
        this.players = players;
        this.userInput = userInput;
        this.nextMatch = false;
        this.matchCounter = 1;
    }

    public void start() {

        System.out.println("OX game");
        System.out.println("Input move in format 'x y' e.g. '1 2'");
        turn();
    }

    private void turn() {

        for(Player player: players) {
            System.out.println(player.getPlayerName() + " move:");

            while (!player.move(userInput.readMove())) {
                System.out.println("Incorrect move, please try again");
            }

            moves += 1;
            display.showMap();

            if (winConditions.check(player)) {
                System.out.println(player.getPlayerName() + " wins match!");
                player.addScore("WIN");
                nextMatch = true;
                matchCounter += 1;
                break;
            } else if (moves == (gameMap.width * gameMap.height)) {
                System.out.println("DRAW!");
                players.get(0).addScore("DRAW");
                players.get(1).addScore("DRAW");
                nextMatch = true;
                matchCounter += 1;
                break;
            }
        }
        endTurn();
    }

    private void endTurn() {
        if (matchCounter == 4) {
            // check points, end game
            int result = new PlayerComparator().compare(players.get(0), players.get(1));
            if (result > 0) {
                System.out.println(players.get(0).getPlayerName() + " won game!");
            } else if (result < 0) {
                System.out.println(players.get(1).getPlayerName() + " won game!");
            } else
                System.out.println("The game ends with a draw");
            System.exit(0);
        } else if (nextMatch) {
            gameMap.reset();
            moves = 0;
            System.out.println("Next match");
            Collections.reverse(players);
            nextMatch = false;
        }
        turn();
    }
}