import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static String[][] graph = new String[5][5];
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            graph[i] = br.readLine().split("");
        };
        br.close();

        rec(0, -1, new boolean[5][5]);
        System.out.println(result);
    }

    private static void rec(int depth, int selected, boolean[][] visited) {
        if (depth == 7) {
            if(is7(visited))
                result++;
            return;
        }

        for (int i = selected + 1; i < 25; i++) {
            int y = i / 5;
            int x = i % 5;
            visited[y][x] = true;
            rec(depth + 1, i, visited);
            visited[y][x] = false;
        }
    }

    private static boolean is7(boolean[][] visited) {
        int startX = -1, startY = -1;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (visited[y][x]) {
                    startX = x;
                    startY = y;
                }
            }
        }

        Queue<Point> qu = new LinkedList<>();
        boolean[][] search = new boolean[5][5];
        qu.offer(new Point(startX, startY));
        search[startY][startX] = true;

        int[] dirX = {0, 0, -1, 1};
        int[] dirY = {-1, 1, 0, 0};
        int searchCount = 0, countS = 0;
        while (!qu.isEmpty()) {
            Point current = qu.poll();
            searchCount++;
            if(graph[current.y][current.x].equals("S"))
                countS++;

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dirX[i];
                int nextY = current.y + dirY[i];
                if(isOutOfBound(nextX) || isOutOfBound(nextY))
                    continue;
                if(!visited[nextY][nextX] || search[nextY][nextX])
                    continue;

                search[nextY][nextX] = true;
                qu.offer(new Point(nextX, nextY));
            }
        }

        return searchCount == 7 && countS >= 4;
    }

    private static boolean isOutOfBound(int num) {
        return num < 0 || 5 <= num;
    }
}
