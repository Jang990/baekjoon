import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dx = {0, 1, 0 ,-1};
    static int[] dy = {1, 0, -1, 0};
    static int dir = 0;
    static int routeId;
    private static int x;
    private static int y;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int num = Integer.parseInt(br.readLine());
        br.close();
        x = 0;
        y = 0;
        routeId = N * N;
        map[y][x] = routeId--;
        while (canMove())
            move();
        StringBuilder mapString = new StringBuilder();
        StringBuilder locationString = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mapString.append(map[i][j]).append(" ");
                if(map[i][j] == num)
                    locationString.append(i + 1).append(" ").append(j + 1);
            }
            mapString.append("\n");
        }
        mapString.deleteCharAt(mapString.length() - 1);
        mapString.deleteCharAt(mapString.length() - 1);

        System.out.println(mapString);
        System.out.println(locationString);
    }

    private static void move() {
        if(isOutOfBound(nextX(), nextY()) || map[nextY()][nextX()] != 0)
            dir = (dir + 1) % 4;
        x = nextX();
        y = nextY();
        map[y][x] = routeId--;
    }

    private static int nextY() {
        return y + dy[dir];
    }

    private static int nextX() {
        return x + dx[dir];
    }

    private static boolean canMove() {
        return x != map.length / 2 || y != map.length / 2;
    }

    private static boolean isOutOfBound(int x, int y) {
        return x < 0 || map[0].length <= x || y < 0 || map.length <= y;
    }
}
