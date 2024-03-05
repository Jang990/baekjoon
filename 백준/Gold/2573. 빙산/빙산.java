import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] graph;
    static int[][] adjacentWater;
    static int[] dirX = {0, 0, 1, -1},
            dirY = {1, -1, 0, 0};
    static Set<Point> ice = new HashSet<>();
    static Set<RemovedInfo> removedIce = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        graph = new int[n][m];
        adjacentWater = new int[n][m];
        for (int i = 0; i < n; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < m; j++) {
                if(graph[i][j] == 0)
                    continue;
                ice.add(new Point(j, i));
            }
        }
        br.close();

        if (isDivIce() || ice.isEmpty()) {
            System.out.println(0);
            return;
        }

        int year = 0, i = 1;
        while (!ice.isEmpty()) {
            if (!melt()) {
                break;
            }

            if (isDivIce()) {
                year = i;
                break;
            }
            i++;
        }

        System.out.println(year);
    }

    private static int getAdjacentWaterCnt(int i, int j) {
        int result = 0;
        for (int k = 0; k < 4; k++) {
            int nextX = j + dirX[k];
            int nextY = i + dirY[k];
            if(outOfBound(nextX, nextY) || graph[nextY][nextX] > 0)
                continue;

            result++;
        }
        return result;
    }

    private static boolean outOfBound(int x, int y) {
        return 0 > x || x >= graph[0].length || 0 > y || y >= graph.length;
    }

    private static boolean melt() {
        removedIce = new HashSet<>();
        for (Point p : ice) {
            int cnt = getAdjacentWaterCnt(p.y, p.x);
            if(cnt > 0)
                removedIce.add(new RemovedInfo(p, cnt));
        }

        if(removedIce.isEmpty())
            return false;

        for (RemovedInfo removedInfo : removedIce) {
            Point removedLoc = removedInfo.p;
            graph[removedLoc.y][removedLoc.x] -= removedInfo.cnt;
            if(graph[removedLoc.y][removedLoc.x] <= 0)
                ice.remove(removedLoc);
        }
        return true;
    }

    static class RemovedInfo {
        Point p;
        int cnt;

        public RemovedInfo(Point p, int cnt) {
            this.p = p;
            this.cnt = cnt;
        }
    }

    private static boolean isDivIce() {
        boolean[][] visited = new boolean[graph.length][graph[0].length];
        boolean isStart = true;
        for (Point p : ice) {
            if(visited[p.y][p.x])
                continue;

            if (!isStart)
                return true;

            bfs(p, visited);
            isStart = false;
        }
        return false;
    }

    private static void bfs(Point p, boolean[][] visited) {
        Queue<Point> qu = new LinkedList<>();
        qu.offer(p);
        visited[p.y][p.x] = true;

        while (!qu.isEmpty()) {
            Point now = qu.poll();
            int nextX, nextY;
            for (int i = 0; i < 4; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];

                if (outOfBound(nextX, nextY)
                        || visited[nextY][nextX]
                        || graph[nextY][nextX] <= 0)
                    continue;

                visited[nextY][nextX] = true;
                qu.offer(new Point(nextX, nextY));
            }
        }
    }
}
