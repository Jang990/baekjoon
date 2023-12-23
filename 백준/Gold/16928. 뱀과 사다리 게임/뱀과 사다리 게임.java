import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] graph = new int[101];
    private static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        map = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).sum();
        for (int i = 0; i < n; i++) {
            String[] arr = br.readLine().split(" ");
            map.put(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
        }
        br.close();

        bfs(1);
        System.out.println(graph[100]);
    }

    private static void bfs(int start) {
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(start);
        graph[start] = 1;

        while (!qu.isEmpty()) {
            int now = qu.poll();
            for (int i = 1; i <= 6; i++) {
                int next = now + i;
                if (graph[next] != 0 && graph[next] <= graph[now]) {
                    continue;
                }

                if (map.containsKey(next)) {
                    graph[next] = graph[now]+1;
                    next = map.get(next);

                    if (graph[next] != 0 && graph[next] <= graph[now]) {
                        continue;
                    }
                }

                if (next >= 100) {
                    graph[100] = graph[now];
                    return;
                }
                graph[next] = graph[now]+1;
                qu.offer(next);
            }
        }
    }
}
