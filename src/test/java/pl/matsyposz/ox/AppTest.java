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

    @DataProvider(name = "testData")
    public static Object[][] data() {

        return new Object[][] {
                {3,3,3,"0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2"},
                {123,100,3,"0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2"},
                {6,7,3,"0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2,0 0,0 1,1 1,0 2,2 2"},
        };
    }

    @Test(dataProvider = "testData")
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

        GameController gameController = new GameController(gameMap, display, winConditions, players, userInput);

        //when
        gameController.start();

        //then
        assertEquals(gameController.matchCounter, 4);
    }
}