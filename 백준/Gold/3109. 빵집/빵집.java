import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int r, c;
    private static int result = 0;
    private static String[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.valueOf(st.nextToken());
        c = Integer.valueOf(st.nextToken());
        graph = new String[r][c];
        for (int i = 0; i < r; i++) {
            graph[i] = br.readLine().split("");
        }
        br.close();

        for (int i = 0; i < r; i++) {
            dfs(0, i);
        }

        System.out.println(result);

    }

    static int[] dirY = {-1, 0, 1};
    private static boolean dfs(int x, int y) {
        if (x == graph[0].length - 1) {
            graph[y][x] = "-";
            result++;
            return true;
        }

        int nextX = x + 1, nextY;
        for (int i = 0; i < 3; i++) {
            nextY = y + dirY[i];
            if (outOfBound(nextX, nextY)
                    || graph[nextY][nextX].equals("x")
                    || graph[nextY][nextX].equals("-")) {
                continue;
            }

            graph[y][x] = "-";
            if(dfs(nextX, nextY))
                return true;
        }

        return false;
    }

    private static boolean outOfBound(int x, int y) {
        return 0 > x || x >= graph[0].length || 0 > y || y >= graph.length;
    }
}
