import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static boolean[] visited;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());
        graph = new int[N+1][N+1];

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.valueOf(st.nextToken());
            int n2 = Integer.valueOf(st.nextToken());
            int distance = Integer.valueOf(st.nextToken());

            graph[n1][n2] = distance;
            graph[n2][n1] = distance;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.valueOf(st.nextToken());
            int n2 = Integer.valueOf(st.nextToken());
            visited = new boolean[N+1];
            min = Integer.MAX_VALUE;
            dfs(n1, n2, 0);
            sb.append(min + "\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void dfs(int now, int goal, int distance) {
        if(now == goal) {
            min = Math.min(distance, min);
            return;
        }

        for (int i = 0; i < graph[now].length; i++) {
            if(visited[i] || graph[now][i] == 0)
                continue;
            if(distance+graph[now][i] >= min)
                continue;

            visited[i] = true;
            dfs(i, goal, distance+graph[now][i]);
            visited[i] = false;
        }
    }

}
