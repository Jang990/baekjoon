import java.util.*;
import java.util.List;
import java.awt.*;

class Solution {
    static int[][] graph;
    static boolean[][] visited;
    static int[] dirX = {0,0,1,-1},
            dirY = {1, -1, 0, 0};

    public static int[] solution(String[] maps) {
        graph = new int[maps.length][maps[0].length()];
        visited = new boolean[maps.length][maps[0].length()];
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                char now = maps[i].charAt(j);
                if(now == 'X') continue;
                graph[i][j] = now - '0';
            }
        }

        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if(visited[i][j] || graph[i][j] == 0) continue;
                result.add(search(new Point(j, i)));
            }
        }
        
        if(result.size() == 0)
            return new int[] {-1};

        return result.stream().sorted()
                .mapToInt(Integer::valueOf).toArray();
    }

    private static int search(Point start) {
        Queue<Point> qu = new LinkedList<>();
        visited[start.y][start.x] = true;
        qu.offer(start);

        int sum = graph[start.y][start.x];
        while (!qu.isEmpty()) {
            Point now = qu.poll();
            int nextX, nextY;
            for (int i = 0; i < 4; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];

                if(isOutOfBound(nextX, nextY)
                        || visited[nextY][nextX]
                        || graph[nextY][nextX] == 0)
                    continue;

                sum += graph[nextY][nextX];
                visited[nextY][nextX] = true;
                qu.offer(new Point(nextX, nextY));
            }
        }

        return sum;
    }

    private static boolean isOutOfBound(int x, int y) {
        return x < 0 || graph[0].length <= x || y < 0 || graph.length <= y;
    }
}