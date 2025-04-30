import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static String[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        graph = new String[N][N];
        for (int i = 0; i < N; i++) {
            graph[i] = br.readLine().split("");
        }
        br.close();

        boolean[][] visited = new boolean[N][N];
        int normal = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visited[i][j])
                    continue;
                visitArea(new Point(j, i), visited, List.of(graph[i][j]));
                normal++;
            }
        }

        int impaired = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visited[i][j])
                    continue;
                List<String> color;
                if(graph[i][j].equals("R") || graph[i][j].equals("G"))
                    color = List.of("R", "G");
                else
                    color = List.of("B");
                visitArea(new Point(j, i), visited, color);
                impaired++;
            }
        }

        System.out.println(normal + " " + impaired);
    }

    private static void visitArea(Point point, boolean[][] visited, List<String> color) {
        Queue<Point> qu = new LinkedList<>();
        qu.offer(point);
        visited[point.y][point.x] = true;

        int[] dirX = {0, 0, 1, -1};
        int[] dirY = {1, -1, 0, 0};
        while (!qu.isEmpty()) {
            Point current = qu.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dirX[i];
                int nextY = current.y + dirY[i];
                if(outOfBound(nextX, nextY)
                        || visited[nextY][nextX]
                        || !color.contains(graph[nextY][nextX]))
                    continue;

                qu.offer(new Point(nextX, nextY));
                visited[nextY][nextX] = true;
            }
        }
    }

    private static boolean outOfBound(int x, int y) {
        return x < 0 || graph.length <= x || y < 0 || graph.length <= y;
    }
}
