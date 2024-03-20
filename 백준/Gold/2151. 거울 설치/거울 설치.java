import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static String[][] graph;
    static int[][][] visited;
    static Point start, end;
    static int[] dirX = {0, 1, 0, -1},
            dirY = {1, 0, -1 , 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new String[n][n];
        visited = new int[4][n][n];
        for (int i = 0; i < n; i++) {
            graph[i] = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                if (!graph[i][j].equals("#"))
                    continue;
                if(start == null)
                    start = new Point(j, i);
                else
                    end = new Point(j, i);
            }
        }
        br.close();
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Light> qu = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            qu.offer(new Light(start.x, start.y, i));
            visited[i][start.y][start.x] = 1;
        }

        int result = Integer.MAX_VALUE;
        while (!qu.isEmpty()) {
            Light now = qu.poll();
            int nextDir = now.dir;
            int nextX = now.x + dirX[nextDir];
            int nextY = now.y + dirY[nextDir];
            if (outOfBound(nextX, nextY))
                continue;

            if (end.x == nextX && end.y == nextY) {
                result = Math.min(result, visited[nextDir][now.y][now.x] - 1);
                continue;
            }

            int nowStep = visited[nextDir][now.y][now.x];
            if (graph[nextY][nextX].equals("*")
                    || isVisited(nowStep, visited[nextDir][nextY][nextX]))
                continue;

            visited[nextDir][nextY][nextX] = nowStep;
            qu.offer(new Light(nextX, nextY, nextDir));

            if(!graph[nextY][nextX].equals("!"))
                continue;

            int reflectedDir = (nextDir + 1) % 4;
            if (!isVisited(nowStep, visited[reflectedDir][nextY][nextX])) {
                visited[reflectedDir][nextY][nextX] = nowStep + 1;
                qu.offer(new Light(nextX, nextY, reflectedDir));
            }
            reflectedDir = (nextDir == 0 ? 4 : nextDir) - 1;
            if (!isVisited(nowStep, visited[reflectedDir][nextY][nextX])) {
                visited[reflectedDir][nextY][nextX] = nowStep + 1;
                qu.offer(new Light(nextX, nextY, reflectedDir));
            }
        }

        if(result == Integer.MAX_VALUE)
            return 0;

        return result;
    }

    private static boolean isVisited(int nowStep, int nextStep) {
        return nextStep != 0 && nowStep >= nextStep;
    }

    private static boolean outOfBound(int x, int y) {
        return 0 > x || x >= graph.length
                || 0 > y || y >= graph.length;
    }

    static class Light {
        int x, y;
        int dir;

        public Light(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
