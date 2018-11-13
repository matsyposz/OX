package pl.matsyposz.ox;

public class GameMap {

    private Character[][] map;

    GameMap(int width, int height) {
        map = new Character[width][height];
    }

    Boolean setSign(int x, int y, Character sign) {
        try {
            if (map[x][y] == null) {
                map[x][y] = sign;
                return true;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("You can not move beyond map");
        }
        return false;
    }

    Character check(int x, int y) {
        return map[x][y];
    }
}