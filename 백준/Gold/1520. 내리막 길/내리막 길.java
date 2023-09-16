import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static int[][] visited;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());
        graph = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf).toArray();
            Arrays.fill(visited[i], -1);
        }
        br.close();

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int x, int y) {
        if (graph.length == y + 1 && graph[0].length == x + 1) {
            return 1;
        }

        if (visited[y][x] != -1) {
            return visited[y][x];
        }

        int[] dirX = {0,0,1,-1};
        int[] dirY = {1,-1,0,0};

        int nextX, nextY;
        visited[y][x] = 0;
        for (int i = 0; i < 4; i++) {
            nextX = x + dirX[i];
            nextY = y + dirY[i];

            if (outOfBound(nextX, nextY) || graph[y][x] <= graph[nextY][nextX]) {
                continue;
            }

            visited[y][x] += dfs(nextX, nextY);
        }

        return visited[y][x];
    }

    private static boolean outOfBound(int nextX, int nextY) {
        return 0 > nextX || nextX >= graph[0].length || 0 > nextY || nextY >= graph.length;
    }
}
