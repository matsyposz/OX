package pl.matsyposz.ox;

import org.testng.annotations.Test;
import pl.matsyposz.ox.io.UserInput;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test
public class WinConditionTest {

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

    public void testWinRow() {
        // given
        String data = "2 0";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        UserInput userInput = new UserInput(System.in);
        GameMap gameMap = new GameMap(3, 3);
        Player playerO = new Player('O', gameMap);
        WinConditions winConditions = new WinConditions(gameMap, userInput);

        // when
        gameMap.setSign(0, 0, 'O');
        gameMap.setSign(1, 0, 'O');
        playerO.move(userInput.readMove());

        // then
        assertEquals(winConditions.gameMap.check(0,0), Character.valueOf('O'));
        assertEquals(winConditions.gameMap.check(1,0), Character.valueOf('O'));
        assertEquals(winConditions.gameMap.check(2,0), Character.valueOf('O'));
        assertTrue(winConditions.check(playerO));
    }

    public void testWinDiag() {
        // given
        String data = "1 1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        UserInput userInput = new UserInput(System.in);
        GameMap gameMap = new GameMap(3, 3);
        Player playerX = new Player('X', gameMap);
        WinConditions winConditions = new WinConditions(gameMap, userInput);

        // when
        gameMap.setSign(0, 0, 'X');
        gameMap.setSign(2, 2, 'X');
        playerX.move(userInput.readMove());

        // then
        assertEquals(winConditions.gameMap.check(0,0), Character.valueOf('X'));
        assertEquals(winConditions.gameMap.check(1,1), Character.valueOf('X'));
        assertEquals(winConditions.gameMap.check(2,2), Character.valueOf('X'));
        assertTrue(winConditions.check(playerX));
    }

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

    public void testWinOnRectangularMap() {
        // given
        String data = "2 3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        UserInput userInput = new UserInput(System.in);
        GameMap gameMap = new GameMap(12, 54);
        Player playerX = new Player('X', gameMap);
        WinConditions winConditions = new WinConditions(gameMap, userInput);

        // when
        gameMap.setSign(1, 2, 'X');
        gameMap.setSign(0, 1, 'X');
        playerX.move(userInput.readMove());

        // then
        assertEquals(winConditions.gameMap.check(1,2), Character.valueOf('X'));
        assertEquals(winConditions.gameMap.check(0,1), Character.valueOf('X'));
        assertEquals(winConditions.gameMap.check(2,3), Character.valueOf('X'));
        assertTrue(winConditions.check(playerX));
    }
}