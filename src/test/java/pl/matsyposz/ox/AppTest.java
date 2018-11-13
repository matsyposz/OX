package pl.matsyposz.ox;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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

        // when
        Boolean result = playerO.move(userInput.readMove());

        // then
        assertFalse(result);
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

    @Ignore
    @Test
    public void testWinConditions() {
        // given
        GameMap gameMap = new GameMap(3, 3);
        WinConditions winConditions = new WinConditions(gameMap);

        // when
        gameMap.setSign(1, 0, 'O');
        gameMap.setSign(1, 1, 'O');
        gameMap.setSign(1, 2, 'O');

        // then
        assertEquals(winConditions.gameMap.check(1,0), Character.valueOf('O'));
        assertEquals(winConditions.gameMap.check(1,1), Character.valueOf('O'));
        assertEquals(winConditions.gameMap.check(1,2), Character.valueOf('O'));
        // TODO check() implementation
        assertTrue(winConditions.check());
    }
}