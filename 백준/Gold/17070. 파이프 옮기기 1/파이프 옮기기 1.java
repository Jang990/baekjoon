import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] graph;
    static int result = 0;
    static int[] dirX = {0, 1, 1},
            dirY = {1, 1, 0};
    static int[][] dir = {{0, 1, -1}, {0, 1, 2}, {1, 2, -1}};
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        dfs(1, 0, 2);

        System.out.println(result);
    }

    private static void dfs(int x, int y, int nowDir) {
        if (x == n - 1 && y == n - 1) {
            result++;
            return;
        }

        for (int nextDir : dir[nowDir]) {
            if(nextDir == -1) continue;

            int nextX = x + dirX[nextDir];
            int nextY = y + dirY[nextDir];
            if(isOutOfBound(nextX, nextY) || isWrongDir(nextX, nextY, nextDir))
                continue;
            dfs(nextX, nextY, nextDir);
        }
    }

    static int[] checkDirX = {0, -1, -1, 0},
            checkDirY = {0, 0, -1, -1};
    private static boolean isWrongDir(int nextX, int nextY, int dir) {
        if (dir != 1) {
            return graph[nextY][nextX] != 0;
        }

        for (int i = 0; i < 4; i++) {
            int checkX = nextX + checkDirX[i];
            int checkY = nextY + checkDirY[i];
            if(graph[checkY][checkX] == 1)
                return true;
        }
        return false;
    }

    private static boolean isOutOfBound(int x, int y) {
        return 0 > x || x >= n || 0 > y || y >= n;
    }
}
