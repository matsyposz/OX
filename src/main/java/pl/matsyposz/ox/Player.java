package pl.matsyposz.ox;

public class Player {

    private Character sign;

    Player(Character sign) {
        this.sign = sign;
    }

    void move(GameMap gameMap, Integer[] coordinates) {
        gameMap.setSign(coordinates[0], coordinates[1], sign);
    }

    Character getSign() {
        return sign;
    }
}