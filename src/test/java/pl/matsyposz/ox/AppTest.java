package pl.matsyposz.ox;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pl.matsyposz.ox.io.Display;
import pl.matsyposz.ox.io.UserInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class AppTest {

    @Mock
    private GameMap gameMap;

    @Mock
    private Display display;

    @Mock
    private UserInput userInput;

    @Spy
    private Player playerO = new Player('O', gameMap);

    @Spy
    private Player playerX = new Player('X', gameMap);

    @Mock
    private WinConditions winConditions;

    @BeforeSuite
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDisplay() {
        //given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        GameMap gameMap = new GameMap(3,3);
        Display display = new Display(System.out, userInput.language(), gameMap);

        //when
        gameMap.setSign(2, 0, 'X');
        display.showMap();

        //then
        assertEquals(
                "   0  1  2  \n" +
                "0  -  -  X \n" +
                "1  -  -  - \n" +
                "2  -  -  - \n", outputStream.toString());
        assertNotEquals(
                "   0  1  2  \n" +
                "0  -  -  - \n" +
                "1  -  -  - \n" +
                "2  O  -  - \n", outputStream.toString());
    }

    @Ignore
    @Test
    public void shouldTakeThreeMatchesToEndGame() {
        //this is used only to keep console clear during test runs:
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        //given
        ArrayList<Player> players = new ArrayList<>();
        players.add(playerO);
        players.add(playerX);

        GameController gameController = new GameController(userInput);

        //when
        when(playerO.move(userInput.readMove())).thenReturn(true);
        when(playerX.move(userInput.readMove())).thenReturn(true);
        when(winConditions.check(playerO)).thenReturn(false);
        when(winConditions.check(playerX)).thenReturn(true);
        when(userInput.language()).thenReturn(ResourceBundle.getBundle("pl.matsyposz.ox.language.LanguageResource_en"));

        gameController.start();

        //then
        assertEquals(gameController.matchCounter, 4);
    }

}