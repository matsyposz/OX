package pl.matsyposz.ox.io;

import org.testng.annotations.Test;
import pl.matsyposz.ox.GameMap;
import pl.matsyposz.ox.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

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

    public void shouldSetResourceBundleToPolish() throws UnsupportedEncodingException {
        // given
        String data = "pl";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        UserInput userInput = new UserInput(System.in);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // when
        Display display = new Display(System.out, userInput.language(), new GameMap(3,3));
        display.print("matchDraw");

        // then
        assertEquals(outputStream.toString("UTF-8"), "Mecz kończy się remisem.\n");
    }

    public void shouldSetResourceBundleToEnglish() throws UnsupportedEncodingException {
        // given
        String data = "%@^#%!^#%@!^";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        UserInput userInput = new UserInput(System.in);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // when
        Display display = new Display(System.out, userInput.language(), new GameMap(3,3));
        display.print("matchDraw");

        // then
        assertEquals(outputStream.toString("UTF-8"), "Match ends with a draw.\n");
    }


    public void shouldSetCustomMapSize() {
        // given
        String data = "200 200";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        UserInput userInput = new UserInput(System.in);

        // when
        GameMap gameMap = new GameMap(userInput.mapSize());

        // then
        assertEquals(gameMap.width, 200);
        assertEquals(gameMap.height, 200);
    }

    public void shouldSetMapTo3x3BecauseCustomMapIsTooLarge() {
        // given
        String data = "201 200";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        UserInput userInput = new UserInput(System.in);

        // when
        GameMap gameMap = new GameMap(userInput.mapSize());

        // then
        assertEquals(gameMap.width, 3);
        assertEquals(gameMap.height, 3);
    }

    public void shouldProvideCorrectPlayerName() {
        // given
        String data = "8751569837146187";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        UserInput userInput = new UserInput(System.in);
        GameMap gameMap = new GameMap(3,3);

        // when
        String actual = userInput.name();
        Player player = new Player(actual,'O', gameMap);

        // then
        assertEquals(player.getPlayerName(), actual);
    }

    public void shouldSetDefaultPlayerNameIfInputIsEmpty() {
        // given
        String data = "";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        UserInput userInput = new UserInput(System.in);
        GameMap gameMap = new GameMap(3,3);

        // when
        String actual = userInput.name();
        Player player = new Player(actual,'O', gameMap);

        // then
        assertEquals(player.getPlayerName(), "PlayerO");
    }
}