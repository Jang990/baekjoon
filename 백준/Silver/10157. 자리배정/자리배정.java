import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static boolean[][] map;
    private static int number = 1;
    private static int nowDir = 0;
    private static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
    private static int nowY;
    private static int nowX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.valueOf(st.nextToken());
        int R = Integer.valueOf(st.nextToken());
        int K = Integer.valueOf(br.readLine());

        if (C * R < K) {
            System.out.println(0);
            return;
        }

        map = new boolean[R][C];
        nowY = R-1;
        nowX = 0;
        map[nowY][nowX] = true;
        while (number != K) {
            if (move()) {
                continue;
            }

            changeDirection();
        }

        System.out.println((nowX+1) + " " + (map.length - nowY));



        br.close();
    }

    private static void changeDirection() {
        nowDir = (nowDir+1) % 4;
    }

    static int[] dirX = {0, 1, 0, -1};
    static int[] dirY = {-1, 0, 1, 0};
    private static boolean move() {
        int nextX = dirX[nowDir] + nowX;
        int nextY = dirY[nowDir] + nowY;

        if (outOfBound(nextX, nextY) || map[nextY][nextX]) {
            return false;
        }

        map[nextY][nextX] = true;
        nowX = nextX;
        nowY = nextY;
        number++;
        return true;
    }

    private static boolean outOfBound(int nextX, int nextY) {
        return 0 > nextX || nextX >= map[0].length || 0 > nextY || nextY >= map.length;
    }
}
