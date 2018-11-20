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
                {10,10,3,"0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2"},
                {6,7,3,"0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2"},
        };
    }

    @DataProvider(name = "oneMatch")
    public static Object[][] dataForOneMatch() {

        return new Object[][] {
                // 1. diagonal wins
                {10,10,3,"0 0,0 1,1 1,0 2,2 2"},
                // 2.
                {6,7,3,"0 0,0 1,1 1,0 2,2 2"},
                // 3. 5 long line, 6x3 map size, win in row
                {6,3,5,"1 0,0 2,2 0,1 2,3 0,2 2,4 0,3 2,5 0"},
                // 4. 5x6 map, win in column
                {5,6,3,"2 1,3 0,2 2,3 1,2 3"},
                // 5. 10x4 map, anti-diagonal win
                {10,4,3,"0 0,3 1,1 0,2 2,3 3,1 3"},
                // 3x3 map:
                // 6. draw
                {3,3,3,"0 0,0 1,0 2,1 1,1 0,2 2,2 1,2 0,1 2"},
                // 7. draw, different moves
                //{3,3,3,""},
                // 8. row 0 win

                // 9. row 1 win

                // 10. row 2 win

                // 11. column 0 win

                // 12. column 1 win

                // 13. column 2 win

                // 14. diagonal win
                {3,3,3,"0 0,0 1,1 1,0 2,2 2"},
                // 15. anti-diagonal win
                {3,3,3,"0 0,2 0,1 0,1 1,2 2,0 2"},
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