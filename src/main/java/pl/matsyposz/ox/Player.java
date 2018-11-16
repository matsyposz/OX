package pl.matsyposz.ox;

import pl.matsyposz.ox.utils.MatchResults;

public class Player {

    private Character sign;
    private GameMap gameMap;
    private String playerName;
    private int score;

    public Player(Character sign, GameMap gameMap) {
        this.sign = sign;
        this.gameMap = gameMap;
        this.playerName = "Player" + sign;
    }

    public Player(String playerName, Character sign, GameMap gameMap) {
        this.sign = sign;
        this.gameMap = gameMap;
        if (playerName == null)
            this.playerName = "Player" + sign;
        else
            this.playerName = playerName;
    }

    public Boolean move(Integer[] coordinates) {
        if (coordinates != null)
            return gameMap.setSign(coordinates[0], coordinates[1], sign);
        else
            return false;
    }

    public Character getSign() {
        return sign;
    }

    public String getPlayerName() {
        return playerName;
    }

    void addScore(MatchResults result) {
        this.score += result.getValue();
    }

    public int getScore() {
        return score;
    }
}