package pl.matsyposz.ox.io;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pl.matsyposz.ox.GameMap;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

@Test
public class DisplayTest {

    @Mock
    private UserInput userInput;

    @BeforeSuite
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    public void testDisplay() throws UnsupportedEncodingException {
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
                        "2  -  -  - \n", outputStream.toString("UTF-8"));
        assertNotEquals(
                "   0  1  2  \n" +
                        "0  -  -  - \n" +
                        "1  -  -  - \n" +
                        "2  O  -  - \n", outputStream.toString("UTF-8"));
    }
}