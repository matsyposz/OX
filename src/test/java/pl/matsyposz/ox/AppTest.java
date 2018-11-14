package pl.matsyposz.ox;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.testng.Assert.*;

public class AppTest {

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
    public void testWinColumn() {
        // given
        String data = "1 2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        UserInput userInput = new UserInput(System.in);
        GameMap gameMap = new GameMap(3, 3);
        Player playerO = new Player('O', gameMap);
        WinConditions winConditions = new WinConditions(gameMap, userInput);

        // when
        gameMap.setSign(1, 0, 'O');
        gameMap.setSign(1, 1, 'O');
        playerO.move(userInput.readMove());

        // then
        assertEquals(winConditions.gameMap.check(1,0), Character.valueOf('O'));
        assertEquals(winConditions.gameMap.check(1,1), Character.valueOf('O'));
        assertEquals(winConditions.gameMap.check(1,2), Character.valueOf('O'));
        assertTrue(winConditions.check(playerO));
    }

    @Test
    public void testWinAntiDiag() {
        // given
        String data = "1 1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        UserInput userInput = new UserInput(System.in);
        GameMap gameMap = new GameMap(3, 3);
        Player playerX = new Player('X', gameMap);
        WinConditions winConditions = new WinConditions(gameMap, userInput);

        // when
        gameMap.setSign(2, 0, 'X');
        gameMap.setSign(0, 2, 'X');
        playerX.move(userInput.readMove());

        // then
        assertEquals(winConditions.gameMap.check(2,0), Character.valueOf('X'));
        assertEquals(winConditions.gameMap.check(0,2), Character.valueOf('X'));
        assertEquals(winConditions.gameMap.check(1,1), Character.valueOf('X'));
        assertTrue(winConditions.check(playerX));
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

    @Ignore
    @Test
    public void testGameFlow() {
        //given
        String data = "1 1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        UserInput userInput = new UserInput(System.in);

        GameMap gameMap = new GameMap(3, 3);
        Display display = new Display(System.out, gameMap);
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player('O', gameMap));
        players.add(new Player('X', gameMap));
        WinConditions winConditions = new WinConditions(gameMap, userInput);

        GameController gameController = new GameController(gameMap, display, winConditions, players, userInput);

        //when
        gameController.start();

        //then

    }

    @Ignore
    @Test
    public void testScore() {
        //given
        GameMap gameMap = new GameMap(3, 3);
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player('O', gameMap));
        players.add(new Player('X', gameMap));

        //then


    }
}