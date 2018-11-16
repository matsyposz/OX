package pl.matsyposz.ox;

import pl.matsyposz.ox.io.UserInput;

class WinConditions {

    final GameMap gameMap;
    private final UserInput userInput;
    private final int scoresToWin = 3;

    WinConditions(GameMap gameMap, UserInput userInput) {
        this.gameMap = gameMap;
        this.userInput = userInput;
    }

    public Boolean check(Player player) {
        Character sign = player.getSign();
        int x = userInput.getLastMove()[0];
        int y = userInput.getLastMove()[1];

        return checkRow(x, y, sign) || checkColumn(x, y, sign) || checkDiagonal(x, y, sign) || checkAntiDiagonal(x, y, sign);
    }

    private boolean checkColumn(int x, int y, Character sign) {
        int scores = 1;

        for (int i = y - 1; i >= 0; i--) {
            if (gameMap.check(x, i).equals(sign) && scores < scoresToWin) {
                scores += 1;
            } else
                break;
        }

        for (int i = y + 1; i < gameMap.height; i++) {
            if (gameMap.check(x, i).equals(sign) && scores < scoresToWin){
                scores += 1;
            } else
                break;
        }

        return scores == scoresToWin;
    }

    private boolean checkRow(int x, int y, Character sign) {
        int scores = 1;

        for (int i = x - 1; i >= 0; i--) {
            if (gameMap.check(i, y).equals(sign) && scores < scoresToWin) {
                scores += 1;
            } else
                break;
        }

        for (int i = x + 1; i < gameMap.width; i++) {
            if (gameMap.check(i, y).equals(sign) && scores < scoresToWin){
                scores += 1;
            } else
                break;
        }

        return scores == scoresToWin;
    }

    private boolean checkDiagonal(int x, int y, Character sign) {
        int scores = 1;
        int i = 1;

        while ((x - i) >= 0 && (y - i) >= 0) {
            if (gameMap.check(x - i, y - i).equals(sign) && scores < scoresToWin) {
                scores += 1;
                i += 1;
            } else
                break;
        }

        i = 1;

        while ((x + i) < gameMap.width && (y + i) < gameMap.height) {
            if (gameMap.check(x + i, y + i).equals(sign) && scores < scoresToWin) {
                scores += 1;
                i += 1;
            } else
                break;
        }

        return scores == scoresToWin;
    }

    private boolean checkAntiDiagonal(int x, int y, Character sign) {
        int scores = 1;
        int i = 1;

        while ((x + i) < gameMap.width && (y - i) >= 0) {
            if (gameMap.check(x + i, y - i).equals(sign) && scores < scoresToWin) {
                scores += 1;
                i += 1;
            } else
                break;
        }

        i = 1;

        while ((x - i) >= 0 && (y + i) < gameMap.height) {
            if (gameMap.check(x - i, y + i).equals(sign) && scores < scoresToWin) {
                scores += 1;
                i += 1;
            } else
                break;
        }

        return scores == scoresToWin;
    }
}
