import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int n;
    private static int[][] map, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.valueOf(br.readLine());
        map = new int[n][n];
        count = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf).toArray();
        }
        br.close();

        int answer = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(dfs(j, i), answer);
            }
        }

        System.out.println(answer);
    }

    static int[] dirX = {0,0,1,-1};
    static int[] dirY = {1,-1,0,0};
    private static int dfs(int x, int y) {
        if (count[y][x] != 0) {
            return count[y][x];
        }

        int nextX, nextY;
        int max = 0;
        for (int i = 0; i < 4; i++) {
            nextX = x + dirX[i];
            nextY = y + dirY[i];

            if(outOfBound(nextX, nextY)
                    || map[nextY][nextX] <= map[y][x])
                continue;

            max = Math.max(dfs(nextX, nextY), max);
        }
        count[y][x] = max + 1;

        return count[y][x];
    }

    private static boolean outOfBound(int x, int y) {
        return 0 > x || x >= n || 0 > y || y >= n;
    }
}
