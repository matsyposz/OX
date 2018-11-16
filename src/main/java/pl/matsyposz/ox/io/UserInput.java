package pl.matsyposz.ox.io;

import pl.matsyposz.ox.UserExitException;

import java.io.InputStream;
import java.util.*;

public class UserInput {

    private InputStream inputStream;
    private Integer[] lastMove;
    public Queue<String> moves;

    public UserInput(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public UserInput(InputStream inputStream, Queue<String> moves) {
        this.inputStream = inputStream;
        this.moves = moves;
    }

    public Integer[] readMove() {
        Integer[] coordinates = new Integer[2];
        try {
            Scanner scanner = new Scanner(inputStream);
            String input = scanner.nextLine();
            if (input.equals("/exit")) {
                throw new UserExitException();
            } else {
                coordinates[0] = Integer.parseInt(input.split(" ")[0]);
                coordinates[1] = Integer.parseInt(input.split(" ")[1]);
                this.lastMove = coordinates;
            }
            return coordinates;
        }catch(InputMismatchException e){
            return null;
        }catch(NumberFormatException e){
            return null;
        } catch (UserExitException e) {
            System.exit(0);
        } catch (NoSuchElementException e) {
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
        return null;
    }

    public Integer[] readMove(Queue<String> moves) {

        Integer[] coordinates = new Integer[2];
        try {
            String input = moves.remove();
            if (input.equals("/exit")) {
                throw new UserExitException();
            } else {
                coordinates[0] = Integer.parseInt(input.split(" ")[0]);
                coordinates[1] = Integer.parseInt(input.split(" ")[1]);
                this.lastMove = coordinates;
            }
            return coordinates;
        }catch(InputMismatchException e){
            return null;
        }catch(NumberFormatException e){
            return null;
        } catch (UserExitException e) {
            System.exit(0);
        } catch (NoSuchElementException e) {
            return null;
        }
        return null;
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
        } catch (NoSuchElementException e) {
            System.exit(0);
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

        } catch (NoSuchElementException e) {
            System.exit(0);
        }
        mapSize.put("width", 3);
        mapSize.put("height", 3);

        return mapSize;
    }

    public String name() {
        try {
            Scanner scanner = new Scanner(inputStream);

            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                return input;
            }
            else
                return null;
        } catch(InputMismatchException e) {
            //e.printStackTrace();
        } catch (NoSuchElementException e) {

        }
        return null;
    }

}
