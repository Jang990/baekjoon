import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    static boolean[] visited;
    static int lastNode = 0, max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        initGraph(n);

        for (int i = 0; i < n - 1; i++) {
            String[] line = br.readLine().split(" ");
            int node1 = Integer.parseInt(line[0]);
            int node2 = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            graph.get(node1).put(node2, weight);
            graph.get(node2).put(node1, weight);
        }

        br.close();

        visited = new boolean[n+1];
        visited[1] = true;
        dfs(1, 0);

        visited = new boolean[n+1];
        visited[lastNode] = true;
        dfs(lastNode, 0);

        System.out.println(max);
    }

    private static void dfs(int now, int sum) {
        Map<Integer, Integer> links = graph.get(now);
        for (int next : links.keySet()) {
            if(visited[next])
                continue;

            visited[next] = true;
            dfs(next, sum + links.get(next));
        }

        if (max < sum) {
            lastNode = now;
            max = sum;
        }
    }

    private static void initGraph(int n) {
        for (int i = 1; i <= n; i++) {
            graph.put(i, new HashMap<>());
        }
    }
}
