package pl.matsyposz.ox;

import pl.matsyposz.ox.io.Display;
import pl.matsyposz.ox.io.UserInput;
import pl.matsyposz.ox.utils.MatchResults;
import pl.matsyposz.ox.utils.PlayerComparator;

import java.util.ArrayList;
import java.util.Collections;

class GameController {

    private Display display;
    private final ArrayList<Player> players;
    private final UserInput userInput;
    private WinConditions winConditions;
    private GameMap gameMap;
    private Boolean nextMatch;
    private final Boolean testData;
    int matchCounter;
    private int moves;
    private int matchToEndGame;

    GameController(UserInput userInput) {
        this.userInput = userInput;
        this.nextMatch = false;
        this.matchCounter = 1;
        this.players = new ArrayList<>();
        this.testData = false;
        this.matchToEndGame = 3;
    }

    GameController(GameMap gameMap, Display display, WinConditions winConditions, ArrayList<Player> players, UserInput userInput, Integer matchToEndGame) {
        this.gameMap = gameMap;
        this.display = display;
        this.winConditions = winConditions;
        this.testData = true;
        this.userInput = userInput;
        this.nextMatch = false;
        this.matchCounter = 1;
        this.players = players;
        this.matchToEndGame = matchToEndGame;
    }

    void start() {
        if (!testData) {
            System.out.println("Please choose language: \n press enter - english, \n type 'pl' - polish");
            this.display = new Display(System.out, userInput.language(), gameMap);

            display.print("description");
            System.out.println();
            display.print("input");
            this.gameMap = new GameMap(userInput.mapSize());
            display.setGameMap(gameMap);
            display.showMap();

            // players input name and whos gonna start
            display.print("userName1");
            String player1Name = userInput.name();

            display.print("userName2");
            String player2Name = userInput.name();

            if (players.size() == 0) {
                players.add(new Player(player1Name, 'O', gameMap));
                players.add(new Player(player2Name, 'X', gameMap));
            }

            // win conditions input here
            display.print("scoresToWin");
            winConditions = new WinConditions(gameMap, userInput, userInput.scoresToWin());
        }
        turn();
    }

    private void turn() {

        for(Player player: players) {
            display.print(player.getPlayerName(), "move");

            if(!testData) {
                while (!player.move(userInput.readMove())) {
                    display.print("wrongMove");
                }
            } else {
                while (!player.move(userInput.readMove(userInput.moves))) {
                    display.print("wrongMove");
                }
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
        if (matchCounter > matchToEndGame) {
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