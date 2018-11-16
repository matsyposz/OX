package pl.matsyposz.ox;

import pl.matsyposz.ox.io.UserInput;

class App {
    private static GameController gameController;

    private static void gameInit() {
        UserInput userInput = new UserInput(System.in);

        gameController = new GameController(userInput);
    }

    public static void main(String[] args) {

        gameInit();
        gameController.start();
    }
}