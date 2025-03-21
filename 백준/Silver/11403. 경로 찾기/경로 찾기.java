import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] graph;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        br.close();

        boolean[][] result = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            visit(i, visited);
            result[i] = visited;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char c = result[i][j] ? '1' : '0';
                sb.append(c + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void visit(int node, boolean[] visited) {
        for (int x = 0; x < N; x++) {
            if(visited[x] || graph[node][x] == 0 || node == x)
                continue;
            visited[x] = true;
            visit(x, visited);
        }
    }
}
