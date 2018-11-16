package pl.matsyposz.ox.io;

import org.testng.annotations.Test;
import pl.matsyposz.ox.GameMap;
import pl.matsyposz.ox.Player;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

@Test
public class UserInputTest {

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

    public void testSignOverwrite() {
        // given
        GameMap gameMap = new GameMap(3, 3);

        // when
        gameMap.setSign(0, 0, 'O');
        gameMap.setSign(0, 0, 'X');

        // then
        assertEquals(gameMap.check(0, 0), Character.valueOf('O'));
    }
}
