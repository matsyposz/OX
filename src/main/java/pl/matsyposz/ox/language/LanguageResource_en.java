package pl.matsyposz.ox.language;

import java.util.ListResourceBundle;

public class LanguageResource_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"description",
                        "OX Game. \n" +
                        "After three matches, player with higher score wins.\n" +
                        "One win is worth 3 points and draw 1 point for each player.\n" +
                        "You need to provide move in format:\n" +
                        "'x y' where x is the column number from 0 to map width - 1\n" +
                        "and y is the row number from 0 to map height - 1\n"
                },
                {"input",
                        "Please provide map size in format 'width height'\n" +
                        "Or press enter to set map to default size 3x3.\n"},
                {"move"," move:\n"},
                {"wrongMove","This move is not allowed, please try again:\n"},
                {"matchWin","The match was won by "},
                {"matchDraw","Match ends with a draw.\n"},
                {"gameWin"," won game, congrats!\n"},
                {"gameDraw","Game ends with a draw.\n"},
                {"nextMatch","Starting next match...\n"},
                {"moveBeyondMap","You can not move beyond map, please provide number in range 0 to map width/height - 1.\n"},
                {"player","Player "},
        };
    }
}