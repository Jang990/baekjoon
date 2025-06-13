import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        br.close();

        System.out.println(bfs(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
    }

    private static int bfs(int start, int end) {
        int[] visited = new int[100_001];
        Arrays.fill(visited, Integer.MAX_VALUE);
        Queue<Integer> qu = new LinkedList<>();

        visited[start] = 0;
        qu.offer(start);
        while (!qu.isEmpty()) {
            int current = qu.poll();

            int next = current * 2;
            if (isInBound(next) && visited[next] > visited[current]) {
                visited[next] = visited[current];
                qu.offer(next);
            }

            move(visited, qu, current, current - 1);
            move(visited, qu, current, current + 1);
        }

        return visited[end];
    }

    private static void move(int[] visited, Queue<Integer> qu, int current, int next) {
        if (isInBound(next) && visited[next] > visited[current]) {
            visited[next] = visited[current] + 1;
            qu.offer(next);
        }
    }

    private static boolean isInBound(int loc) {
        return 0 <= loc && loc <= 100_000;
    }
}
