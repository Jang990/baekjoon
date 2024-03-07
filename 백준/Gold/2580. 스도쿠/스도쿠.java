import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        graph = new int[9][9];
        for (int i = 0; i < 9; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        rec(0, 0);
    }

    private static void rec(int y, int x) {
        if (x == 9) {
            y++;
            x = 0;
        }
        if (y == 9) {
            print();
            System.exit(0);
        }

        if (graph[y][x] != 0) {
            rec(y, x + 1);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (isPossible(x, y, i)) {
                graph[y][x] = i;
                rec(y, x+1);
            }
        }
        graph[y][x] = 0;
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(graph[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static boolean isPossible(int x, int y, int value) {
        return isPossibleW(y, value)
                && isPossibleH(x, value)
                && isPossibleS(x, y, value);
    }

    private static boolean isPossibleS(int x, int y, int value) {
        boolean[] visited = new boolean[10];
        int startX = x / 3 * 3;
        int startY = y / 3 * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                visited[graph[startY + i][startX + j]] = true;
            }
        }

        return !visited[value];
    }

    private static boolean isPossibleH(int x, int value) {
        boolean[] visited = new boolean[10];
        for (int i = 0; i < 9; i++) {
            visited[graph[i][x]] = true;
        }

        return !visited[value];
    }

    private static boolean isPossibleW(int y, int value) {
        boolean[] visited = new boolean[10];
        for (int i = 0; i < 9; i++) {
            visited[graph[y][i]] = true;
        }

        return !visited[value];
    }
}
