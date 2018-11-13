package pl.matsyposz.ox;

public class App {

    private static GameMap gameMap;
    private static Player playerO;
    private static Player playerX;
    private static UserInput userInput;
    private static WinConditions winConditions;

    private static void gameInit() {
        gameMap = new GameMap(3, 3);
        playerO = new Player('O', gameMap);
        playerX = new Player('X', gameMap);
        userInput = new UserInput(System.in);
        winConditions = new WinConditions(gameMap, userInput);
    }

    public static void main(String[] args) {

        gameInit();

        System.out.println("OX game");
        System.out.println("Input move in format 'x y' np. '1 2'");

        int moveCounter = 0;

        //TODO
        do {
            System.out.println("PlayerO move:");
            while (!playerO.move(userInput.readMove())) {
                System.out.println("Incorrect move, please try again");
            }
            moveCounter += 1;
            if (winConditions.check(playerO)) {
                System.out.println("PlayerO wins!");
                break;
            }

            System.out.println("PlayerX move:");
            while (!playerX.move(userInput.readMove())) {
                System.out.println("Incorrect move, please try again");
            }
            moveCounter += 1;
            if (winConditions.check(playerX)) {
                System.out.println("PlayerX wins!");
                break;
            }

        }while (moveCounter != 9);

        System.out.println("END");
    }
}