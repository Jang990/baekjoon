import java.awt.*;
import java.util.*;

class Solution {
    static int[][] graph;
    static int[][] visited;

    public static int solution(int[][] maps) {
        graph = maps;
        visited = new int[maps.length][maps[0].length];

        bfs();

        int result = visited[maps.length - 1][maps[0].length - 1];
        if(result == 0)
            return -1;
        else
            return result;
    }

    private static void bfs() {
        Queue<Point> qu = new LinkedList<>();
        qu.offer(new Point(0, 0));
        visited[0][0] = 1;

        int[] dirX = {0, 0, -1, 1};
        int[] dirY = {-1, 1, 0, 0};
        while (!qu.isEmpty()) {
            Point current = qu.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dirX[i];
                int nextY = current.y + dirY[i];
                if(isOutOfBound(nextX, nextY)
                        || graph[nextY][nextX] == 0
                        || (visited[nextY][nextX] != 0 && visited[current.y][current.x] + 1 >= visited[nextY][nextX]))
                    continue;

                qu.offer(new Point(nextX, nextY));
                visited[nextY][nextX] = visited[current.y][current.x] + 1;
            }
        }
    }

    private static boolean isOutOfBound(int x, int y) {
        return y < 0 || graph.length <= y || x < 0 || graph[0].length <= x;
    }
}