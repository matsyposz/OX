package pl.matsyposz.ox;

import java.io.InputStream;
import java.util.Scanner;

public class UserInput {

    private InputStream inputStream;
    private Scanner scanner;

    public UserInput(InputStream inputStream) {
        this.inputStream = inputStream;
        this.scanner = new Scanner(inputStream);
    }

    public Integer[] readMove() {
        System.out.println("Input next move: np. 1 2");

        Integer[] coordinates = new Integer[2];
        coordinates[0] = scanner.nextInt();
        coordinates[1] = scanner.nextInt();

        return coordinates;
    }
}
