package pl.matsyposz.ox;

public class Player {

    private Character sign;
    private GameMap gameMap;
    private String playerName;
    private int score;

    Player(Character sign, GameMap gameMap) {
        this.sign = sign;
        this.gameMap = gameMap;
        this.playerName = "Player" + sign;
    }

    Player(String playerName, Character sign, GameMap gameMap) {
        this.sign = sign;
        this.gameMap = gameMap;
        this.playerName = playerName;
    }

    Boolean move(Integer[] coordinates) {
        if (coordinates != null)
            return gameMap.setSign(coordinates[0], coordinates[1], sign);
        else
            return false;
    }

    Character getSign() {
        return sign;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void addScore(String result) {
        if (result.equals("WIN")) {
            this.score += 3;
        }
        else if (result.equals("DRAW")) {
            this.score += 1;
        }
    }

    public int getScore() {
        return score;
    }
}