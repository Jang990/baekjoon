import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[][] map;
    static int[][] visited;
    private static int endX, endY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] cond = line.split(" ");
        endX = Integer.parseInt(cond[0]);
        endY = Integer.parseInt(cond[1]);
        map = new int[endY][endX];
        visited = new int[endY][endX];

        for (int i = 0; i < endY; i++) {
            map[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Point> qu = new LinkedList<>();
        visited[0][0] = 1;
        qu.offer(new Point(0, 0));

        int[] dirX = {0, 0, 1, -1},
                dirY = {1, -1, 0, 0};
        while (!qu.isEmpty()) {
            Point now = qu.poll();
            int nowVisited = visited[now.y][now.x];
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];
                if(outOfBound(nextX, nextY)) continue;

                int cond = 0;
                if (map[nextY][nextX] == 1) {
                    cond = 1;
                }
                int nextVisited = nowVisited + cond;
                if(visited[nextY][nextX] != 0 && visited[nextY][nextX] <= nextVisited)
                    continue;
                visited[nextY][nextX] = nextVisited;
                qu.offer(new Point(nextX, nextY));
            }
        }

        return visited[endY - 1][endX - 1] - 1;
    }

    private static boolean outOfBound(int x, int y) {
        return 0 > x || x >= endX || 0 > y || y >= endY;
    }
}
