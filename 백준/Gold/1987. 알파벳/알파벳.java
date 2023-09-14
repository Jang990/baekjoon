import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[][] graph;
    private static boolean[] visited;
    private static int maxStep = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.valueOf(st.nextToken());
        int C = Integer.valueOf(st.nextToken());
        graph = new int[R][C];
        visited = new boolean[26];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                graph[i][j] = line.charAt(j) - 'A';
            }
        }
        br.close();

        visited[graph[0][0]] = true;
        dfs(0, 0, 1);

        System.out.println(maxStep);
    }

    private static void dfs(int x, int y, int step) {
        maxStep = Math.max(step, maxStep);
        int[] dirX = {0,0,1,-1};
        int[] dirY = {1,-1,0,0};

        for (int i = 0; i < 4; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];
            if(outOfBound(nextX, nextY) || visited[graph[nextY][nextX]]) {
                continue;
            }

            visited[graph[nextY][nextX]] = true;
            dfs(nextX,nextY, step + 1);
            visited[graph[nextY][nextX]] = false;
        }
    }

    private static boolean outOfBound(int x, int y) {
        return 0 > x || x >= graph[0].length || 0 > y || y >= graph.length;
    }
}
