import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static String[][] map;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] point = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        map = new String[point[0]][point[1]];

        for (int i = 0; i < point[0]; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).toArray(String[]::new);
        }

        br.close();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].equals("W")) {
                    continue;
                }

                Point start = new Point(j, i);
                BFS(start);
            }
        }

        System.out.println(max);
    }

    private static void BFS(Point start) {
        Queue<Point> qu = new LinkedList<>();
        int[][] step = new int[map.length][map[0].length];
        step[start.y][start.x] = 1; // 0은 방문하지 않은 곳
        qu.offer(start);

        int[] dirX = {0,0,1,-1};
        int[] dirY = {1,-1,0,0};
        int nextX, nextY;
        while (!qu.isEmpty()) {
            Point now = qu.poll();
            for (int i = 0; i < dirX.length; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];
                if (outOfRange(nextX, nextY) || step[nextY][nextX] != 0 || map[nextY][nextX].equals("W")) {
                    continue;
                }

                step[nextY][nextX] = step[now.y][now.x]+1;
                qu.offer(new Point(nextX, nextY));
                max = Math.max(max, step[nextY][nextX]-1); // 시작을 1로 했기 때문에 -1을 해줌
            }
        }

    }

    private static boolean outOfRange(int nextX, int nextY) {
        return nextX < 0 || map[0].length <= nextX || nextY < 0 || map.length <= nextY;
    }
}
