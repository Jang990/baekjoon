import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static int floor;
    private static int start;
    private static int end;
    private static int[] visited;
    private static int Up;
    private static int Down;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        br.close();

        floor = arr[0];
        start = arr[1];
        end = arr[2];
        Up = arr[3];
        Down = arr[4];
        visited = new int[floor + 1];
        visited[start] = 1;

        int result = bfs(floor, start, end);
        if (result == -1) {
            System.out.println("use the stairs");
            return;
        }

        System.out.println(result);
    }

    private static int bfs(int floor, int start, int end) {
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(start);

        while (!qu.isEmpty()) {
            int now = qu.poll();
            if (now == end) {
                return visited[end] - 1;
            }

            int next = now + Up;
            if (next <= floor) {
                checkVisited(now, next, qu);
            }

            next = now - Down;
            if (next >= 1) {
                checkVisited(now, next, qu);
            }
        }

        return -1;
    }

    private static void checkVisited(int now, int next, Queue<Integer> qu) {
        if (visited[next] == 0) {
            visited[next] = visited[now] + 1;
            qu.offer(next);
        }
    }
}
