package pl.matsyposz.ox;

public class GameMap {

    Character[][] map;

    GameMap() {
        map = new Character[3][3];
    }

    Character check(int x, int y) {
        return map[x][y];
    }
}