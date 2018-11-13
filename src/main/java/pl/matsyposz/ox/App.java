package pl.matsyposz.ox;

public class App {

    public static void main(String[] args) {
        /*
        1. Start
        2. Player O moves
            if move is wrong try again
        3. Player X moves
            if move is wrong try again
        4. check win condition
            if true
                exit
            else
                loop 2 -> 5
         */

        System.out.println("OX game");
        System.out.println("Input move in format '1 2'");

        GameMap gameMap = new GameMap(3, 3);
        WinConditions winConditions = new WinConditions(gameMap);
        Player playerO = new Player('O', gameMap);
        Player playerX = new Player('X', gameMap);
        UserInput userInput = new UserInput(System.in);

        int moveCounter = 0;

        // Game loop
        do {
            System.out.println("PlayerO move:");
            while (!playerO.move(userInput.readMove())) {
                System.out.println("Incorrect move, please try again");
            }
            moveCounter += 1;
            if (winConditions.check() || moveCounter == 9)
                break;

            System.out.println("PlayerX move:");
            while (!playerX.move(userInput.readMove())) {
                System.out.println("Incorrect move, please try again");
            }
            moveCounter += 1;

        }while (!winConditions.check() || moveCounter != 9);

        System.out.println("END");
    }
}