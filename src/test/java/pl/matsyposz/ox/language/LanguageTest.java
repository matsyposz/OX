package pl.matsyposz.ox.language;

import org.mockito.Mock;
import org.testng.annotations.Test;
import pl.matsyposz.ox.GameMap;
import pl.matsyposz.ox.io.Display;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.testng.AssertJUnit.assertEquals;

@Test
public class LanguageTest {

    @Mock
    GameMap gameMap;

    public void shouldDisplayMessagesInEnglish() throws UnsupportedEncodingException {
        // given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("pl.matsyposz.ox.language.LanguageResource_en", new Locale("en","US"));
        Display display = new Display(System.out, resourceBundle, gameMap);

        // when
        display.print("input");

        // then
        assertEquals(outputStream.toString("UTF-8"),
                "Please provide map size in format 'width height'\n" +
                        "Or press enter to set map to default size 3x3.\n" +
                        "Maximum value of width/height is 10\n");
    }

    public void shouldDisplayMessagesInPolish() throws UnsupportedEncodingException {
        // given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("pl.matsyposz.ox.language.LanguageResource_pl", new Locale("pl", "PL"));
        Display display = new Display(System.out, resourceBundle, gameMap);

        // when
        display.print("wrongMove");

        // then
        assertEquals(outputStream.toString("UTF-8"), "Ten ruch jest niepoprawny, proszę podać inną pozycję:\n");
    }
}