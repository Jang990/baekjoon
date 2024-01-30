import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static String[][] graph;
    static Point goal, start;
    static int[][] waterVisited;
    static int[] dirX = {0, 0, 1, -1};
    static int[] dirY = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] cond = br.readLine().split(" ");
        int R = Integer.parseInt(cond[0]);
        int C = Integer.parseInt(cond[1]);
        graph = new String[R][C];

        for (int i = 0; i < R; i++) {
            graph[i] = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                if (graph[i][j].equals("D")) {
                    goal = new Point(j, i);
                } else if (graph[i][j].equals("S")) {
                    start = new Point(j, i);
                }
            }
        }

        br.close();

        spreadWater();
        int result = bfs();
        if (result == Integer.MIN_VALUE)
            System.out.println("KAKTUS");
        else
            System.out.println(result);
    }

    private static int bfs() {
        int[][] visited = new int[graph.length][graph[0].length];
        Queue<Point> qu = new LinkedList<>();
        qu.offer(start);
        visited[start.y][start.x] = 1;

        while (!qu.isEmpty()) {
            Point now = qu.poll();
            int nowStep = visited[now.y][now.x];
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];
                if (isOutOfBound(nextX, nextY) || graph[nextY][nextX].equals("X") || isDangerArea(nextX, nextY, nowStep))
                    continue;
                if(visited[nextY][nextX] != 0 && visited[nextY][nextX] <= nowStep + 1)
                    continue;

                Point next = new Point(nextX, nextY);
                if (goal.equals(next))
                    return nowStep;
                visited[nextY][nextX] = nowStep + 1;
                qu.offer(next);
            }

        }

        return Integer.MIN_VALUE;
    }

    private static boolean isDangerArea(int x, int y, int nowStep) {
        return waterVisited[y][x] != 0 && waterVisited[y][x] <= nowStep + 1;
    }

    private static void spreadWater() {
        Queue<Point> qu = new LinkedList<>();
        waterVisited = new int[graph.length][graph[0].length];

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (graph[i][j].equals("*")) {
                    qu.offer(new Point(j, i));
                    waterVisited[i][j] = 1;
                }
            }
        }

        while (!qu.isEmpty()) {
            Point now = qu.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];
                if (isOutOfBound(nextX, nextY) || waterVisited[nextY][nextX] != 0
                        || graph[nextY][nextX].equals("D") || graph[nextY][nextX].equals("X"))
                    continue;

                waterVisited[nextY][nextX] = waterVisited[now.y][now.x] + 1;
                qu.offer(new Point(nextX, nextY));
            }
        }
    }

    private static boolean isOutOfBound(int x, int y) {
        return 0 > x || x >= graph[0].length || 0 > y || y >= graph.length;
    }
}
