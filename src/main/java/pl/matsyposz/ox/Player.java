package pl.matsyposz.ox;

public class Player {

    private Character sign;

    Player(Character sign) {
        this.sign = sign;
    }

    void move(GameMap gameMap, int x, int y) {
        gameMap.map[x][y] = sign;
    }

    Character getSign() {
        return sign;
    }
}