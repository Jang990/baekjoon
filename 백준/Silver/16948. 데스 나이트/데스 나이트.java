import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int boardSize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boardSize = Integer.valueOf(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Point now = new Point(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
        Point goal = new Point(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
        br.close();

        int result = bfs(now, goal);
        System.out.println(result);
    }


    static final int[] dirX = {-2,-2,0,0,2,2};
    static final int[] dirY = {-1,1,-2,2,-1,1};

    private static int bfs(Point knight, Point goal) {
        Queue<Point> qu = new LinkedList<>();
        int[][] visited = new int[boardSize][boardSize];
        qu.offer(knight);
        visited[knight.y][knight.x] = 1;

        while (!qu.isEmpty()) {
            Point now = qu.poll();
            int nextX, nextY;
            for (int i = 0; i < 6; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];

                if(outOfBound(nextX, nextY) || visited[nextY][nextX] != 0)
                    continue;

                int nowStep = visited[now.y][now.x];
                if(nextX == goal.x && nextY == goal.y) {
                    return nowStep;
                }

                qu.offer(new Point(nextX, nextY));
                visited[nextY][nextX] = nowStep + 1;
            }
        }

        return -1;
    }

    private static boolean outOfBound(int x, int y) {
        return 0 > x || x >= boardSize || 0 > y || y >= boardSize;
    }
}
