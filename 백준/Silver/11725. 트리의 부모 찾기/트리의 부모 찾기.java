import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Integer, List<Integer>> map = new HashMap<>();
    static boolean[] visited;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        visited = new boolean[N+1];
        parent = new int[N+1];

        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.valueOf(st.nextToken());
            int n2 = Integer.valueOf(st.nextToken());
            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }

        visited[1] = true;
        dfs(1, 0);
        visited[1] = false;

        br.close();

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]+"\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int now, int beforeNode) {
        parent[now] = beforeNode;

        List<Integer> lines = map.get(now);
        for (int line : lines) {
            if (visited[line]) {
                continue;
            }

            visited[line] = true;
            dfs(line, now);
            visited[line] = false;
        }
    }
}
