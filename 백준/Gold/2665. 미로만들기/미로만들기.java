import java.io.*;
import java.util.*;

public class Main {
    static int[][] graph;
    static boolean[][][] visited;
    private static int maxChanged;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        maxChanged = n * 2 - 2;
        initGraph(br, n);
        visited = new boolean[maxChanged][n][n];
        br.close();

        search();
        int minChanged = 0;
        while (!visited[minChanged][n - 1][n - 1]) {
            minChanged++;
        }
        System.out.println(minChanged);
    }

    private static void initGraph(BufferedReader br, int n) throws IOException {
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            graph[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
        }
    }

    private static void search() {
        Queue<MyPoint> qu = new LinkedList<>();
        qu.offer(new MyPoint(0, 0, 0));
        visited[0][0][0] = true;

        int[] dirX = {0, 0, 1, -1};
        int[] dirY = {1, -1, 0, 0};
        while (!qu.isEmpty()) {
            MyPoint current = qu.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dirX[i];
                int nextY = current.y + dirY[i];
                int nextChanged = current.changed;

                if(outOfBound(nextX,nextY))
                    continue;
                if(isWall(nextX, nextY))
                    nextChanged++;
                if(nextChanged >= maxChanged)
                    continue;
                if(visited[nextChanged][nextY][nextX])
                    continue;

                qu.offer(new MyPoint(nextX, nextY, nextChanged));
                visited[nextChanged][nextY][nextX] = true;
            }
        }
    }

    private static boolean outOfBound(int x, int y) {
        return 0 > x || x >= graph[0].length || 0 > y || y >= graph.length;
    }

    private static boolean isWall(int x, int y) {
        return graph[y][x] == 0;
    }

    static class MyPoint {
        int x, y, changed;

        public MyPoint(int x, int y, int changed) {
            this.x = x;
            this.y = y;
            this.changed = changed;
        }
    }
}
