package pl.matsyposz.ox;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AppTest {

    @Test
    public void testMove() {
        // given
        Player playerO = new Player('O');
        Player playerX = new Player('X');
        GameMap gameMap = new GameMap();

        // when
        playerO.move(gameMap,0, 0);
        playerX.move(gameMap,0, 1);

        // then
        assertEquals(gameMap.check(0,0), playerO.getSign());
        assertEquals(gameMap.check(0,1), playerX.getSign());
    }
}