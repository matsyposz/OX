package pl.matsyposz.ox;

import pl.matsyposz.ox.io.Display;
import pl.matsyposz.ox.io.UserInput;
import pl.matsyposz.ox.utils.MatchResults;
import pl.matsyposz.ox.utils.PlayerComparator;

import java.util.ArrayList;
import java.util.Collections;

class GameController {

    private Display display;
    private ArrayList<Player> players;
    private UserInput userInput;
    private WinConditions winConditions;
    private GameMap gameMap;
    private Boolean nextMatch;
    int matchCounter;
    private int moves;

    GameController(UserInput userInput) {
        this.userInput = userInput;
        this.nextMatch = false;
        this.matchCounter = 1;
        this.players = new ArrayList<>();
    }

    void start() {
        System.out.println("Please choose language: \n press enter - english, \n type 'pl' - polish");
        this.display = new Display(System.out, userInput.language(), gameMap);

        display.print("description");
        display.print("input");
        this.gameMap = new GameMap(userInput.mapSize());
        display.setGameMap(gameMap);
        display.showMap();

        // players input name and whos gonna start
        players.add(new Player('O', gameMap));
        players.add(new Player('X', gameMap));

        // win conditions input here
        winConditions = new WinConditions(gameMap, userInput);

        turn();
    }

    private void turn() {

        for(Player player: players) {
            display.print(player.getPlayerName(), "move");

            while (!player.move(userInput.readMove())) {
                display.print("wrongMove");
            }

            moves += 1;
            display.showMap();

            if (winConditions.check(player)) {
                player.addScore(MatchResults.WIN);
                display.printScores(player.getPlayerName(), players);
                nextMatch = true;
                matchCounter += 1;
                break;
            } else if (moves == (gameMap.width * gameMap.height)) {
                display.print("matchDraw");
                players.get(0).addScore(MatchResults.DRAW);
                players.get(1).addScore(MatchResults.DRAW);
                nextMatch = true;
                matchCounter += 1;
                break;
            }
        }
        endTurn();
    }

    private void endTurn() {
        if (matchCounter == 4) {
            int result = new PlayerComparator().compare(players.get(0), players.get(1));
            if (result > 0) {
                display.print(players.get(0).getPlayerName(), "gameWin");
            } else if (result < 0) {
                display.print(players.get(1).getPlayerName(), "gameWin");
            } else
                display.print("gameDraw");
        } else if (nextMatch) {
            gameMap.reset();
            moves = 0;
            display.print("nextMatch");
            Collections.reverse(players);
            nextMatch = false;
            turn();
        } else
            turn();
    }
}