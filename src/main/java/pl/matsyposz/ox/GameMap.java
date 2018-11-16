package pl.matsyposz.ox;

public class GameMap {

    private Character[][] map;
    public int width;
    public int height;

    public GameMap(int width, int height) {
        map = new Character[width][height];
        this.width = width;
        this.height = height;
    }

    public Boolean setSign(int x, int y, Character sign) {
        try {
            if (map[x][y] == null) {
                map[x][y] = sign;
                return true;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            //e.printStackTrace();
        }
        return false;
    }

    public Character check(int x, int y) {
        if(map[x][y] != null)
            return map[x][y];
        else
            return '-';
    }

    void reset() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++)
                map[i][j] = null;
        }
    }
}