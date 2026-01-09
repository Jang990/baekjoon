import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static boolean hasCycle = false;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            int edgeCnt = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreElements())
                graph[i].add(Integer.parseInt(st.nextToken()));
        }
        br.close();

        visited[1] = true;
        dfs(1);

        if(hasCycle)
            System.out.println("CYCLE");
        else
            System.out.println("NO CYCLE");
    }

    private static void dfs(int node) {
        if(hasCycle)
            return;
        for (int next : graph[node]) {
            if (visited[next]) {
                hasCycle = true;
                return;
            }

            visited[next] = true;
            dfs(next);
            visited[next] = false;
        }
    }
}
