package pl.matsyposz.ox;

public class GameMap {

    private Character[][] map;
    int size;

    GameMap(int width, int height) {
        map = new Character[width][height];
        this.size = 3;
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
        if(map[x][y] != null)
            return map[x][y];
        else
            return '-';
    }
}