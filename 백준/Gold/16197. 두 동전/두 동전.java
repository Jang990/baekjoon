import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static String[][] map;
    private static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] cond = br.readLine().split(" ");
        map = new String[Integer.parseInt(cond[0])][Integer.parseInt(cond[1])];
        visited = new int[Integer.parseInt(cond[0])][Integer.parseInt(cond[1])];
        for (int i = 0; i < map.length; i++) {
            map[i] = br.readLine().split("");
        }
        br.close();

        Point c1 = null, c2 = null;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(!map[i][j].equals("o"))
                    continue;
                if (c1 == null)
                    c1 = new Point(j, i);
                else
                    c2 = new Point(j, i);
            }
        }

        int result = bfs(new Status(c1, c2, 0));
        System.out.println(result);
    }

    private static int bfs(Status startStatus) {
        Queue<Status> qu = new LinkedList<>();

        visited[startStatus.coin1.y][startStatus.coin1.x] = 1;
        visited[startStatus.coin2.y][startStatus.coin2.x] = 1;
        qu.offer(startStatus);

        int[] dirX = {0, 0, 1, -1};
        int[] dirY = {1, -1, 0, 0};
        while (!qu.isEmpty()) {
            Status now = qu.poll();
            if(now.step >= 10)
                return -1;

            int nextStep = now.step + 1;
            for (int i = 0; i < 4; i++) {
                Point c1 = new Point(now.coin1.x + dirX[i], now.coin1.y + dirY[i]);
                Point c2 = new Point(now.coin2.x + dirX[i], now.coin2.y + dirY[i]);

                if (isWall(c1)) {
                    c1.x -= dirX[i];
                    c1.y -= dirY[i];
                }
                if (isWall(c2)) {
                    c2.x -= dirX[i];
                    c2.y -= dirY[i];
                }

                if(outOfBound(c1) && outOfBound(c2))
                    continue;

                if(outOfBound(c1) || outOfBound(c2))
                    return nextStep;

                qu.offer(new Status(c1, c2, nextStep));
            }
        }

        return -1;
    }

    private static boolean isWall(Point c1) {
        return !outOfBound(c1) && map[c1.y][c1.x].equals("#");
    }

    static boolean outOfBound(Point p) {
        return 0 > p.x || p.x >= map[0].length || 0 > p.y || p.y >= map.length;
    }

    static class Status {
        final Point coin1, coin2;
        final int step;

        public Status(Point coin1, Point coin2, int step) {
            this.coin1 = coin1;
            this.coin2 = coin2;
            this.step = step;
        }
    }
}
