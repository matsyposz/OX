package pl.matsyposz.ox;

import java.io.InputStream;
import java.util.InputMismatchException;
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
}
