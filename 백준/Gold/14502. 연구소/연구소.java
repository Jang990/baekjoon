import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    static int[][] graph;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] cond = input(br);
        graph = new int[cond[0]][cond[1]];

        for (int i = 0; i < graph.length; i++)
            graph[i] = input(br);
        br.close();

        rec(0, new Stack<>());
        System.out.println(max);
    }

    private static void rec(int depth, Stack<Point> selected) {
        if (depth == 3) {
            max = Math.max(max, checkArea(selected));
            return;
        }

        int start = 0;
        if (!selected.isEmpty()) {
            start = selected.peek().y * graph[0].length + selected.peek().x + 1;
        }

        for (int i = start; i < graph.length * graph[0].length; i++) {
            int x = i % graph[0].length;
            int y = i / graph[0].length;
            if(graph[y][x] != 0)
                continue;
            selected.push(new Point(x, y));
            rec(depth + 1, selected);
            selected.pop();
        }
    }

    private static int checkArea(Stack<Point> selected) {
        checkToGraph(selected, 1);
        Queue<Point> qu = new LinkedList<>();
        boolean[][] visited = new boolean[graph.length][graph[0].length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if(graph[i][j] != 2)
                    continue;
                qu.offer(new Point(j, i));
                visited[i][j] = true;
            }
        }

        int[] dirX = {0, 0, 1, -1};
        int[] dirY = {1, -1, 0, 0};
        int nextX, nextY;
        while (!qu.isEmpty()) {
            Point current = qu.poll();
            for (int i = 0; i < 4; i++) {
                nextX = current.x + dirX[i];
                nextY = current.y + dirY[i];

                if(outOfBound(nextX, nextY)
                        || visited[nextY][nextX]
                        || graph[nextY][nextX] != 0)
                    continue;

                qu.offer(new Point(nextX, nextY));
                visited[nextY][nextX] = true;
            }
        }

        int result = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if(graph[i][j] == 0 && !visited[i][j])
                    result++;
            }
        }

        checkToGraph(selected, 0);
        return result;
    }

    private static boolean outOfBound(int x, int y) {
        return x < 0 || graph[0].length <= x || y < 0 || graph.length <= y;
    }

    private static void checkToGraph(Stack<Point> stack, int num) {
        for (Point point : stack)
            graph[point.y][point.x] = num;
    }

    private static int[] input(BufferedReader br) throws IOException {
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        return input;
    }
}
