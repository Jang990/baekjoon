import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static List<int[]> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arg = br.readLine().split(" ");
        int n = Integer.parseInt(arg[0]);

        for (int i = 0; i < n; i++) {
            graph.add(
                    Arrays.stream(br.readLine().split(""))
                            .mapToInt(Integer::parseInt)
                            .toArray()
            );
        }

        br.close();

        System.out.println(bfs());
    }

    static int[] dirX = {0, 0, 1, -1};
    static int[] dirY = {1, -1, 0, 0};
    private static int bfs() {
        Queue<Point> qu = new LinkedList<>();
        int[][] visited = new int[graph.size()][graph.get(0).length];
        qu.offer(new Point(0, 0));
        visited[0][0] = 1;

        while (!qu.isEmpty()) {
            Point current = qu.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dirX[i];
                int nextY = current.y + dirY[i];
                if(isOutOfBound(nextX, nextY)
                        || graph.get(nextY)[nextX] == 0
                        || (visited[nextY][nextX] != 0 && visited[current.y][current.x] + 1 >= visited[nextY][nextX]))
                    continue;
                visited[nextY][nextX] = visited[current.y][current.x] + 1;
                qu.offer(new Point(nextX, nextY));
            }
        }

        return visited[visited.length - 1][visited[0].length - 1];
    }

    private static boolean isOutOfBound(int x, int y) {
        return y < 0 || graph.size() <= y || x < 0 || graph.get(0).length <= x;
    }
}
