import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static boolean[][] visited;
    static String[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new String[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).toArray(String[]::new);
        }
        br.close();

        int normalCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j]) {
                    continue;
                }
                bfs(new Point(j,i), map[i][j]);
                normalCnt++;
            }
        }

        int rgCnt = 0;
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j]) {
                    continue;
                }
                Point now = new Point(j, i);
                if(map[i][j].equals("R") || map[i][j].equals("G")) {
                    bfs(now, "R", "G");
                }
                else {
                    bfs(now, "B");
                }
                rgCnt++;
            }
        }

        System.out.println(normalCnt + " " + rgCnt);
    }


    static int dirX[] = {0,0,1,-1};
    static int dirY[] = {1,-1,0,0};

    private static void bfs(Point start, String... colors) {
        Queue<Point> qu = new LinkedList<>();
        qu.offer(start);
        visited[start.y][start.x] = true;

        while (!qu.isEmpty()) {
            Point now = qu.poll();
            int nextX, nextY;
            for (int i = 0; i < 4; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];

                if(outOfBoundary(nextX, nextY) || visited[nextY][nextX]) {
                    continue;
                }

                for (String color : colors) {
                    if (map[nextY][nextX].equals(color)) {
                        qu.offer(new Point(nextX, nextY));
                        visited[nextY][nextX] = true;
                        break;
                    }
                }

            }
        }
    }

    private static boolean outOfBoundary(int nextX, int nextY) {
        return 0 > nextX || nextX >= map[0].length || 0 > nextY || nextY >= map.length;
    }
}
