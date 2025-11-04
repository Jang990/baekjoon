import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static int[][] graph;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            graph[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        br.close();

        List<Integer> groups = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(graph[i][j] == 0 || visited[i][j])
                    continue;
                groups.add(bfs(j, i));
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(groups.size()).append("\n");
        groups.stream().sorted()
                .forEach(i -> sb.append(i).append("\n"));
        System.out.println(sb);
    }

    static int[] dirX = {0, 0, 1, -1};
    static int[] dirY = {1, -1, 0, 0};
    private static int bfs(int x, int y) {
        int result = 1;
        Queue<Point> qu = new LinkedList<>();
        qu.offer(new Point(x, y));
        visited[y][x] = true;

        while (!qu.isEmpty()) {
            Point current = qu.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dirX[i];
                int nextY = current.y + dirY[i];
                if (isOutOfBound(nextX, nextY)
                        || graph[nextY][nextX] == 0
                        || visited[nextY][nextX])
                    continue;

                qu.offer(new Point(nextX, nextY));
                visited[nextY][nextX] = true;
                result++;
            }
        }
        return result;
    }

    private static boolean isOutOfBound(int x, int y) {
        return x < 0 || graph[0].length <= x
                || y < 0 || graph.length <= y;
    }
}
