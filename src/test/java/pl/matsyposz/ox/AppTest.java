package pl.matsyposz.ox;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.matsyposz.ox.io.Display;
import pl.matsyposz.ox.io.UserInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.testng.Assert.*;

public class AppTest {

    @DataProvider(name = "threeMatches")
    public static Object[][] dataForThreeMatches() {

        return new Object[][] {
                {3,3,3,"0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2"},
                {123,100,3,"0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2"},
                {6,7,3,"0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2"},
        };
    }

    @DataProvider(name = "oneMatch")
    public static Object[][] dataForOneMatch() {

        return new Object[][] {
                // diagonal wins
                {3,3,3,"0 0,0 1,1 1,0 2,2 2"},
                {12,10,3,"0 0,0 1,1 1,0 2,2 2"},
                {6,7,3,"0 0,0 1,1 1,0 2,2 2"},
                // 5 long line, 6x3 map size, win in row
                {6,3,5,"1 0,0 2,2 0,1 2,3 0,2 2,4 0,3 2,5 0"},
                // 4x5 map, win in column
                {5,6,3,"2 1,3 0,2 2,3 1,2 3"},
                // 12x3 map, antidiagonal win
                {12,4,3,"0 0,3 1,1 0,2 2,3 3,1 3"},
                // 3x3 map, draw
                {3,3,3,"0 0,0 1,0 2,1 1,1 0,2 2,2 1,2 0,1 2"}
        };
    }

    @Test(dataProvider = "oneMatch")
    public void shouldTakeOneMatchToEndGame(Integer width, Integer height, Integer scoresToWin ,String data) {
        //this is used only to keep console clear during test runs:
        //ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //System.setOut(new PrintStream(outputStream));

        //given
        String[] splitMoves = data.split(",");
        LinkedList<String> moves = new LinkedList<>();
        Collections.addAll(moves, splitMoves);
        GameMap gameMap = new GameMap(width, height);
        UserInput userInput = new UserInput(System.in, moves);
        Display display = new Display(System.out, ResourceBundle.getBundle("pl.matsyposz.ox.language.LanguageResource_en", new Locale("en","US")), gameMap);

        WinConditions winConditions = new WinConditions(gameMap, userInput, scoresToWin);

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player('O', gameMap));
        players.add(new Player('X', gameMap));

        GameController gameController = new GameController(gameMap, display, winConditions, players, userInput, 1);

        //when
        gameController.start();

        //then
        assertEquals(gameController.matchCounter, 2);
    }

    @Test(dataProvider = "threeMatches")
    public void shouldTakeThreeMatchesToEndGame(Integer width, Integer height, Integer scoresToWin ,String data) {
        //this is used only to keep console clear during test runs:
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        //given
        String[] splitMoves = data.split(",");
        LinkedList<String> moves = new LinkedList<>();
        Collections.addAll(moves, splitMoves);
        GameMap gameMap = new GameMap(width, height);
        UserInput userInput = new UserInput(System.in, moves);
        Display display = new Display(System.out, ResourceBundle.getBundle("pl.matsyposz.ox.language.LanguageResource_en", new Locale("en","US")), gameMap);

        WinConditions winConditions = new WinConditions(gameMap, userInput, scoresToWin);

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player('O', gameMap));
        players.add(new Player('X', gameMap));

        GameController gameController = new GameController(gameMap, display, winConditions, players, userInput, 3);

        //when
        gameController.start();

        //then
        assertEquals(gameController.matchCounter, 4);
    }
}