import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] arr, isCycle;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (testCase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            isCycle = new int[n+1];
            arr = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i + 1] = Integer.parseInt(st.nextToken());
            }

            visited = new boolean[n+1];
            for (int i = 1; i <= n; i++) {
                if(isCycle[i] != 0)
                    continue;
                if(dfs(i, arr[i]))
                    isCycle[i] = 1;
                else
                    isCycle[i] = -1;
            }

            int result = 0;
            for (int i = 1; i <= n; i++) {
                if(isCycle[i] == -1)
                    result++;
            }
            sb.append(result).append("\n");
        }

        br.close();
        System.out.println(sb);
    }

    private static boolean dfs(int start, int now) {
        if (isCycle[now] != 0) {
            return false;
        }

        if (start == now || visited[now]) {
            isCycle[now] = 1;
            return true;
        }

        visited[now] = true;
        boolean dfs = dfs(start, arr[now]);
        if (dfs && isCycle[now] == 0) {
            isCycle[now] = 1;
            return true;
        }
        if(isCycle[now] == 0)
            isCycle[now] = -1;
        return false;
    }
}
