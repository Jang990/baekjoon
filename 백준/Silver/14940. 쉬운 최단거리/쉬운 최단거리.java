import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] opt = br.readLine().split(" ");
        int N = Integer.valueOf(opt[0]);
        int M = Integer.valueOf(opt[1]);

        graph = new int[N][M];
        for (int i = 0; i < N; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        }
        br.close();

        Point start = new Point();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 2) {
                    start.x = j;
                    start.y = i;
                }
            }
        }

        int[][] result = bfs(start);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (result[i][j] == 0 && graph[i][j] == 1) {
                    sb.append("-1 ");
                }
                else {
                    sb.append(result[i][j] + " ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int[][] bfs(Point start) {
        Queue<Point> qu = new LinkedList<>();
        qu.offer(start);
        int[][] result = new int[graph.length][graph[0].length];

        int[] dirX = {0, 0, 1, -1};
        int[] dirY = {1, -1, 0, 0};
        while (!qu.isEmpty()) {
            Point now = qu.poll();
            int nextX, nextY;
            int nowDistance = result[now.y][now.x];
            for (int i = 0; i < dirX.length; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];


                if (outOfBound(nextX, nextY)
                        || graph[nextY][nextX] == 2
                        || graph[nextY][nextX] == 0) {
                    continue;
                }

                if (result[nextY][nextX] == 0 || nowDistance + graph[nextY][nextX] < result[nextY][nextX]) {
                    result[nextY][nextX] = nowDistance + graph[nextY][nextX];
                    qu.offer(new Point(nextX, nextY));
                }
            }
        }

        return result;
    }

    private static boolean outOfBound(int nextX, int nextY) {
        return 0 > nextX || nextX >= graph[0].length || 0 > nextY || nextY >= graph.length;
    }
}
