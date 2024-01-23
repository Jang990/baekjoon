import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static String[][][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new String[9][8][8];
        for (int i = 0; i < 8; i++) {
            map[0][i] = br.readLine().split("");
        }
        br.close();

        initMap();
        if(bfs(new Point(0, 7)))
            System.out.println(1);
        else
            System.out.println(0);
    }

    private static boolean bfs(Point point) {
        Queue<Player> qu = new LinkedList<>();
        qu.offer(new Player(point, 0));

        int[] dirX = {-1,0,1,-1,0,1,-1,0,1};
        int[] dirY = {-1,-1,-1,0,0,0,1,1,1};
        int nextX, nextY;
        while (!qu.isEmpty()) {
            Player now = qu.poll();
            Point nowLoc = now.loc;

            String[][] nowMap = getMap(now.step);
            String[][] nextMap = getMap(now.step + 1);
            for (int i = 0; i < 9; i++) {
                nextX = nowLoc.x + dirX[i];
                nextY = nowLoc.y + dirY[i];
                if(outOfBound(nextX, nextY)
                        || nowMap[nextY][nextX].equals("#")
                        || nextMap[nextY][nextX].equals("#"))
                    continue;

                if(nextX == 7 && nextY == 0)
                    return true;

                qu.offer(new Player(new Point(nextX, nextY), now.step + 1));
            }
        }

        return false;
    }

    private static boolean outOfBound(int x, int y) {
        return 0 > x || x >= 8 || 0 > y || y >= 8;
    }

    private static String[][] getMap(int step) {
        if(step >= map.length)
            return map[8];
        return map[step];
    }

    static class Player {
        Point loc;
        int step;

        public Player(Point loc, int step) {
            this.loc = loc;
            this.step = step;
        }
    }

    private static void initMap() {
        for (int i = 1; i < 9; i++) {
            Arrays.fill(map[i][7], ".");
            for (int j = 6; j >= 0; j--) {
                for (int k = 0; k < 8; k++) {
                    if(map[i-1][j][k].equals("#"))
                        map[i][j + 1][k] = "#";
                    map[i][j][k] = ".";
                }
            }
        }
    }
}
