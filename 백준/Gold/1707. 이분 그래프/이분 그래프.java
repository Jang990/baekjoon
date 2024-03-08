import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (testcase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            graph = new List[V + 1];
            visited = new boolean[V + 1];

            for (int i = 0; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int e1 = Integer.parseInt(st.nextToken());
                int e2 = Integer.parseInt(st.nextToken());
                graph[e1].add(e2);
                graph[e2].add(e1);
            }

            boolean fail = false;
            for (int i = 1; i <= V; i++) {
                if (visited[i])
                    continue;
                if (!bfs(i, V)) {
                    fail = true;
                    break;
                }
            }

            if(fail)
                sb.append("NO\n");
            else
                sb.append("YES\n");
        }
        br.close();
        System.out.println(sb);
    }

    private static boolean bfs(int start, int nodeCnt) {
        Queue<Integer> qu = new LinkedList<>();
        int[] group = new int[nodeCnt + 1];
        group[start] = 1;
        qu.offer(start);

        while (!qu.isEmpty()) {
            int now = qu.poll();
            for (int next : graph[now]) {
                if(group[now] == group[next])
                    return false;
                if(visited[next])
                    continue;

                group[next] = group[now] * -1;
                visited[next] = true;
                qu.offer(next);
            }
        }

        return true;
    }
}
