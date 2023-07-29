import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    static String[][] graph = new String[12][6];
    static boolean[][] explosionCheck = new boolean[12][6];
    static boolean[][] visited = new boolean[12][6];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < graph.length; i++) {
            graph[i] = Arrays.stream(br.readLine().split("")).toArray(String[]::new);
        }
        br.close();

        int result = 0;
        while (isExplosible()) {
            explode();
            visited = new boolean[12][6];
            explosionCheck = new boolean[12][6];
            result++;
        }

        System.out.println(result);
    }

    private static void explode() {
        for (int j = 0; j < graph[0].length; j++) {
            for (int i = graph.length-1; i >= 0; i--) {
                if (graph[i][j].equals(".")) {
                    break;
                }

                if (explosionCheck[i][j]) {
                    graph[i][j] = ".";
                }
            }
            gravity(j);
        }
    }

    private static void gravity(int lineIdx) {
        Queue<String> qu = new LinkedList<>();
        for (int i = graph.length-1; i >= 0; i--) {
            if (graph[i][lineIdx].equals(".")) {
                continue;
            }

            qu.offer(graph[i][lineIdx]);
            graph[i][lineIdx] = ".";
        }

        int idx = graph.length-1;
        while (!qu.isEmpty()) {
            graph[idx][lineIdx] = qu.poll();
            idx--;
        }
    }

    private static boolean isExplosible() {
        boolean explosible = false;
        for (int i = graph.length-1; i >= 0; i--) {
            for (int j = 0; j < graph[0].length; j++) {
                if (graph[i][j].equals(".") || visited[i][j]) {
                    continue;
                }

                boolean explosion = bfs(new Point(j, i), graph[i][j]);
                if (!explosible) {
                    explosible = explosion;
                }
            }
        }
        return explosible;
    }

    private static boolean bfs(Point start, String type) {
        Queue<Point> qu = new LinkedList<>();
        Stack<Point> stack = new Stack<>();
        visited[start.y][start.x] = true;
        qu.offer(start);
        stack.push(start);

        int[] dirX = {0, 0, 1, -1};
        int[] dirY = {1, -1,0,0};
        int nextX, nextY;
        while (!qu.isEmpty()) {
            Point now = qu.poll();

            for (int i = 0; i < 4; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];

                if (outOfBound(nextX, nextY) || visited[nextY][nextX] || !graph[nextY][nextX].equals(type)) {
                    continue;
                }

                Point next = new Point(nextX, nextY);
                qu.offer(next);
                stack.push(next);
                visited[nextY][nextX] = true;
            }
        }

        if (stack.size() >= 4) {
            while (!stack.isEmpty()) {
                Point explosionSpace = stack.pop();
                explosionCheck[explosionSpace.y][explosionSpace.x] = true;
            }
            return true;
        }

        return false;
    }

    private static boolean outOfBound(int nextX, int nextY) {
        return 0 > nextX || nextX >= graph[0].length || 0 > nextY || nextY >= graph.length;
    }

}
