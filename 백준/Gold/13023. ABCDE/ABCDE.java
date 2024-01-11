import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Integer, List<Integer>> map = new HashMap<>();
    static boolean[] visited;
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());

        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            map.put(i, new LinkedList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.valueOf(st.nextToken());
            int n2 = Integer.valueOf(st.nextToken());
            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }
        br.close();

        for (int i = 0; i < n && !flag; i++) {
            visited[i] = true;
            dfs(1, i);
            visited[i] = false;
        }

        if (flag) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static void dfs(int depth, int now) {
        if (depth >= 5) {
            flag = true;
            return;
        }

        for (int next : map.get(now)) {
            if(visited[next])
                continue;

            visited[next] = true;
            dfs(depth + 1, next);
            visited[next] = false;
        }
    }
}
