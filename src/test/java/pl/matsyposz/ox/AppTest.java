package pl.matsyposz.ox;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pl.matsyposz.ox.io.Display;
import pl.matsyposz.ox.io.UserInput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.mockito.Mockito.doReturn;
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
    public void testMove() {
        // given
        String data = "1 2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        UserInput userInput = new UserInput(System.in);
        GameMap gameMap = new GameMap(3, 3);
        Player playerO = new Player('O', gameMap);

        // when
        playerO.move(userInput.readMove());

        // then
        assertEquals(gameMap.check(1, 2), playerO.getSign());
    }

    @Test
    public void testWrongMove() {
        // given
        String data = "0 0";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        UserInput userInput = new UserInput(System.in);
        GameMap gameMap = new GameMap(3, 3);
        Player playerO = new Player('O', gameMap);

        // when
        gameMap.setSign(0, 0, 'X');
        Boolean result = playerO.move(userInput.readMove());

        // then
        assertEquals(gameMap.check(0, 0), Character.valueOf('X'));
        assertFalse(result);
    }

    @Test
    public void testWrongInput() {
        // given
        String data = "daiudhbawud6756%^^ ^&&&";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        UserInput userInput = new UserInput(System.in);
        GameMap gameMap = new GameMap(3, 3);
        Player playerO = new Player('O', gameMap);

        // when then
        assertFalse(playerO.move(userInput.readMove()));
    }

    @Test
    public void testSignOverwrite() {
        // given
        GameMap gameMap = new GameMap(3, 3);

        // when
        gameMap.setSign(0, 0, 'O');
        gameMap.setSign(0, 0, 'X');

        // then
        assertEquals(gameMap.check(0, 0), Character.valueOf('O'));
    }

    @Test
    public void testDisplay() {
        //given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        GameMap gameMap = new GameMap(3,3);
        Display display = new Display(System.out, gameMap);

        //when
        gameMap.setSign(1, 1, 'X');
        display.showMap();

        //then
        assertEquals(" -  -  - \n -  X  - \n -  -  - \n", outputStream.toString());
        assertNotEquals(" -  -  - \n -  -  - \n -  -  O \n", outputStream.toString());
    }

    @Test
    public void shouldTakeThreeMatchesToEndGame() {
        //given
        ArrayList<Player> players = new ArrayList<>();
        players.add(playerO);
        players.add(playerX);

        GameController gameController = new GameController(gameMap, display, winConditions, players, userInput);

        //when
        when(playerO.move(userInput.readMove())).thenReturn(true);
        when(playerX.move(userInput.readMove())).thenReturn(true);
        when(winConditions.check(playerO)).thenReturn(false);
        when(winConditions.check(playerX)).thenReturn(true);

        gameController.start();

        //then
        assertEquals(gameController.matchCounter, 4);
    }

    @Ignore
    @Test
    public void testScore() {
        //given
        ArrayList<Player> players = new ArrayList<>();
        players.add(playerO);
        players.add(playerX);

        //then


    }
}