import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static int[][] graph;
    static int[][] landSeqGraph;
    static boolean[][] visited;
    static int[] dirX = {0, 0, 1, -1},
            dirY = {1, -1, 0, 0};
    static int landSeq = 1;
    static int answer = Integer.MAX_VALUE;
    static List<Point> landPointer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        landSeqGraph = new int[n][n];
        for (int i = 0; i < n; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        br.close();

        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && graph[i][j] == 1) {
                    divLand(i, j);
                }
            }
        }

        for (Point land : landPointer) {
            visited = new boolean[n][n];
            makeBridge(land.x, land.y);
        }

        System.out.println(answer);
    }

    private static void makeBridge(int startX, int startY) {
        Queue<Bridge> qu = new LinkedList<>();
        int startLandSeq = landSeqGraph[startY][startX];
        visited[startY][startX] = true;
        qu.offer(new Bridge(startX, startY, 0));

        while (!qu.isEmpty()) {
            Bridge now = qu.poll();
            Point nowLoc = now.p;
            int nextX, nextY;
            for (int i = 0; i < 4; i++) {
                nextX = nowLoc.x + dirX[i];
                nextY = nowLoc.y + dirY[i];
                if(isOutOfBound(nextX, nextY) || visited[nextY][nextX])
                    continue;

                boolean isSameLand = landSeqGraph[nextY][nextX] == startLandSeq;
                boolean isOcean = graph[nextY][nextX] == 0;
                boolean isOtherLand = !isOcean && !isSameLand;
                int nextLen = isSameLand ? 0 : now.len + 1;

                if (isOtherLand && nextLen <= answer) {
                    answer = now.len;
                    continue;
                }

                if(isOcean && nextLen >= answer)
                    continue;

                visited[nextY][nextX] = true;
                qu.offer(new Bridge(nextX, nextY, nextLen));
            }
        }
    }

    private static void divLand(int startY, int startX) {
        Queue<Point> qu = new LinkedList<>();
        landSeqGraph[startY][startX] = landSeq;
        visited[startY][startX] = true;
        Point start = new Point(startX, startY);
        qu.offer(start);
        landPointer.add(start);

        while (!qu.isEmpty()) {
            Point now = qu.poll();
            int nextX, nextY;
            for (int i = 0; i < 4; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];
                if(isOutOfBound(nextX, nextY) || visited[nextY][nextX]
                        || graph[nextY][nextX] != 1)
                    continue;

                landSeqGraph[nextY][nextX] = landSeq;
                visited[nextY][nextX] = true;
                qu.offer(new Point(nextX, nextY));
            }
        }
        landSeq++;
    }

    private static boolean isOutOfBound(int nextX, int nextY) {
        return 0 > nextX || nextX >= graph[0].length
                || 0 > nextY || nextY >= graph.length;
    }

    static class Bridge {
        Point p;
        int len;

        public Bridge(int x, int y, int len) {
            this.p = new Point(x, y);
            this.len = len;
        }
    }
}
