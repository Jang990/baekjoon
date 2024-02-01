import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    static int max = Integer.MIN_VALUE;
    static int lastNode = 0;
    static boolean[] visited;
    private static int V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        initGraph();

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int baseNode = Integer.parseInt(st.nextToken());

            while (true) {
                int relNode = Integer.parseInt(st.nextToken());
                if(relNode == -1)
                    break;
                int len = Integer.parseInt(st.nextToken());
                graph.get(baseNode).put(relNode, len);
            }
        }
        br.close();


        visited = new boolean[V + 1];
        visited[1] = true;
        dfs(1, 0);

        visited = new boolean[V + 1];
        visited[lastNode] = true;
        dfs(lastNode, 0);

        System.out.println(max);
    }

    private static void dfs(int now, int cost) {
        Map<Integer, Integer> links = graph.get(now);
        for (int next : links.keySet()) {
            if(visited[next])
                continue;

            visited[next] = true;
            dfs(next, cost + links.get(next));
        }

        if (max < cost) {
            max = cost;
            lastNode = now;
        }
    }

    private static void initGraph() {
        for (int i = 1; i <= V; i++) {
            graph.put(i, new HashMap<>());
        }
    }
}
