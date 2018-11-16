package pl.matsyposz.ox.io;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UserInput {

    private InputStream inputStream;
    private Integer[] lastMove;

    public UserInput(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Integer[] readMove() {
        Integer[] coordinates = new Integer[2];
        try {
            Scanner scanner = new Scanner(inputStream);
            coordinates[0] = scanner.nextInt();
            coordinates[1] = scanner.nextInt();
            this.lastMove = coordinates;

            return coordinates;
        }catch(InputMismatchException e){
            return null;
        }
    }

    public Integer[] getLastMove() {
        return lastMove;
    }

    public ResourceBundle language() {

        try {
            Scanner scanner = new Scanner(inputStream);

            if (scanner.nextLine().equals("pl")) {
                return ResourceBundle.getBundle("pl.matsyposz.ox.language.LanguageResource_pl", new Locale("pl", "PL"));
            }

        } catch(InputMismatchException e) {
            //e.printStackTrace();
        }
        return ResourceBundle.getBundle("pl.matsyposz.ox.language.LanguageResource_en", new Locale("en", "US"));
    }
}
