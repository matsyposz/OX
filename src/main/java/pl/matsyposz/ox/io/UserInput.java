package pl.matsyposz.ox.io;

import java.io.InputStream;
import java.util.*;

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

    public HashMap<String, Integer> mapSize() {
        HashMap<String, Integer> mapSize = new HashMap<>();

        try {
            Scanner scanner = new Scanner(inputStream);

            String input = scanner.nextLine();
            if (!input.equals("")) {
                int width = Integer.parseInt(input.split(" ")[0]);
                int height = Integer.parseInt(input.split(" ")[1]);

                if (width <= 200 && height <= 200) {
                    mapSize.put("width", width);
                    mapSize.put("height", height);
                } else
                    throw new InputMismatchException();

                return mapSize;
            }
        } catch(InputMismatchException e) {
            //e.printStackTrace();
        } catch (NumberFormatException e) {

        }
        mapSize.put("width", 3);
        mapSize.put("height", 3);

        return mapSize;
    }
}
