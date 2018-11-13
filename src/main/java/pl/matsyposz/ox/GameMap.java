package pl.matsyposz.ox;

public class GameMap {

    private Character[][] map;

    GameMap(int width, int heigth) {
        map = new Character[width][heigth];
    }

    void setSign(int x, int y, Character sign) {
        if (map[x][y] == null)
            map[x][y] = sign;
    }

    Character check(int x, int y) {
        return map[x][y];
    }
}