import java.awt.*;
import java.util.*;

class Solution {
    static int[] oils;
    public static int solution(int[][] land) {
        oils = new int[land[0].length];
        boolean[][] visited = new boolean[land.length][land[0].length];
        for (int y = 0; y < land.length; y++) {
            for (int x = 0; x < land[0].length; x++) {
                if(visited[y][x] || land[y][x] == 0) continue;
                init(land, visited, new Point(x, y));
            }
        }

        int answer = 0;
        for (int i = 0; i < oils.length; i++) {
            answer = Math.max(answer, oils[i]);
        }
        return answer;
    }

    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};
    private static void init(int[][] land, boolean[][] visited, Point p) {
        Queue<Point> qu = new LinkedList<>();
        Set<Integer> xLoc = new HashSet<>();
        qu.offer(p);
        xLoc.add(p.x);
        visited[p.y][p.x] = true;
        int size = 1;

        while (!qu.isEmpty()) {
            Point now = qu.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];
                if(isOutOfBound(land,nextX,nextY)) continue;
                if(visited[nextY][nextX] || land[nextY][nextX] != 1) continue;

                visited[nextY][nextX] = true;
                qu.offer(new Point(nextX, nextY));
                size++;
                xLoc.add(nextX);
            }
        }

        for (Integer x : xLoc) {
            oils[x] += size;
        }
    }

    private static boolean isOutOfBound(int[][] land, int x, int y) {
        return 0 > x || x >= land[0].length || 0 > y || y >= land.length;
    }
}