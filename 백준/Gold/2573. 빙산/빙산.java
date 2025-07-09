import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int[][] graph;
    static int[][] meltCnt;
    static List<Point> iceLoc = new LinkedList<>();
    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = readLine(br);
        graph = new int[line[0]][line[1]];
        meltCnt = new int[line[0]][line[1]];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = readLine(br);
        }
        for (int y = 0; y < graph.length; y++) {
            for (int x = 0; x < graph[0].length; x++) {
                if (graph[y][x] == 0)
                    continue;
                iceLoc.add(new Point(x, y));
                for (int i = 0; i < 4; i++) {
                    int nextX = dirX[i] + x;
                    int nextY = dirY[i] + y;
                    if(isOutOfBounds(nextX, nextY) || graph[nextY][nextX] != 0)
                        continue;
                    meltCnt[y][x]++;
                }
            }
        }
        br.close();

        int result = 0;
        while (!isDivided(graph)) {
            melt();
            result++;
        }

        if(iceLoc.isEmpty())
            System.out.println(0);
        else
            System.out.println(result);
    }

    private static boolean isDivided(int[][] graph) {
        if(iceLoc.isEmpty())
            return true;

        boolean[][] visited = new boolean[graph.length][graph[0].length];
        Queue<Point> qu = new LinkedList<>();
        Point start = iceLoc.get(0);
        qu.offer(start);
        visited[start.y][start.x] = true;

        while (!qu.isEmpty()) {
            Point current = qu.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = dirX[i] + current.x;
                int nextY = dirY[i] + current.y;
                if(isOutOfBounds(nextX, nextY)
                        || graph[nextY][nextX] <= 0
                        || visited[nextY][nextX])
                    continue;
                visited[nextY][nextX] = true;
                qu.offer(new Point(nextX, nextY));
            }
        }

        for (Point ice : iceLoc) {
            if(!visited[ice.y][ice.x])
                return true;
        }

        return false;
    }

    private static void melt() {
        List<Point> meltAll = new LinkedList<>();
        for (Point ice : iceLoc) {
            if(graph[ice.y][ice.x] <= 0)
                continue;
            graph[ice.y][ice.x] -= meltCnt[ice.y][ice.x];
            if (graph[ice.y][ice.x] > 0)
                continue;

            meltAll.add(ice);
        }

        for (Point melt : meltAll) {
            for (int i = 0; i < 4; i++) {
                int nextX = dirX[i] + melt.x;
                int nextY = dirY[i] + melt.y;
                if(isOutOfBounds(nextX, nextY) || graph[nextY][nextX] <= 0)
                    continue;
                meltCnt[nextY][nextX]++;
            }
        }

        iceLoc.removeAll(meltAll);
    }

    private static int[][] cloneGraph() {
        int[][] result = new int[graph.length][graph[0].length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                result[i][j] = graph[i][j];
            }
        }
        return result;
    }

    private static boolean isOutOfBounds(int x, int y) {
        return x < 0 || graph[0].length <= x || y < 0 || graph.length <= y;
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
