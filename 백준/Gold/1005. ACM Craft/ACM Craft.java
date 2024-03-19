import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] time;
    static int[] createdAt;
    static int[] enter;
    static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (testcase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            createdAt = new int[N + 1];
            graph = new List[N + 1];
            enter = new int[N + 1];
            time = new int[N + 1];
            Arrays.fill(createdAt, Integer.MIN_VALUE);
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                graph[n1].add(n2);
                enter[n2]++;
            }
            int goal = Integer.parseInt(br.readLine());

            sort();
            sb.append(createdAt[goal]).append("\n");
        }
        br.close();
        System.out.println(sb);
    }

    private static void sort() {
        Queue<Integer> qu = new LinkedList<>();
        for (int i = 1; i < enter.length; i++) {
            if (enter[i] == 0) {
                createdAt[i] = time[i];
                qu.offer(i);
            }
        }

        while (!qu.isEmpty()) {
            int now = qu.poll();
            for (int next : graph[now]) {
                enter[next]--;
                createdAt[next] = Math.max(createdAt[next], createdAt[now] + time[next]);
                if (enter[next] == 0)
                    qu.offer(next);
            }
        }
    }
}
