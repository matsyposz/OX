package pl.matsyposz.ox;

import pl.matsyposz.ox.io.UserInput;

public class WinConditions {

    GameMap gameMap;
    UserInput userInput;

    WinConditions(GameMap gameMap, UserInput userInput) {
        this.gameMap = gameMap;
        this.userInput = userInput;
    }

    public Boolean check(Player player) {
        Character sign = player.getSign();
        int x = userInput.getLastMove()[0];
        int y = userInput.getLastMove()[1];
        int n = gameMap.width;

        //check column
        for (int i = 0; i < n; i++) {
            if (!gameMap.check(x, i).equals(sign))
                break;
            else if (i == n - 1)
                return true;
        }

        //check row
        for (int i = 0; i < n; i++) {
            if (!gameMap.check(i, y).equals(sign))
                break;
            else if (i == n - 1)
                return true;
        }

        //check diagonal
        if (x == y) {
            for (int i = 0; i < n; i++){
                if (!gameMap.check(i, i).equals(sign))
                    break;
                else if (i == n - 1)
                    return true;
            }
        }

        //check antidiagonal
        if (x + y == n - 1) {
            for (int i = 0; i < n; i++){
                if (!gameMap.check(i, n - 1 - i).equals(sign))
                    break;
                else if (i == n - 1)
                    return true;
            }
        }

        return false;
    }
}
