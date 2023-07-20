import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[][] graph;
    static int[] visited;
    static final int sangIdx = 1;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];
        visited = new int[N + 1];

        for (int i = 0; i < M; i++) {
            String[] rel = br.readLine().split(" ");
            int n1 = Integer.parseInt(rel[0]);
            int n2 = Integer.parseInt(rel[1]);
            graph[n1][n2] = 1;
            graph[n2][n1] = 1;
        }
        br.close();


        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Integer> qu = new LinkedList<>();
        visited[sangIdx] = 1;
        qu.offer(sangIdx);

        int cnt = 0;
        while (!qu.isEmpty()) {
            int now = qu.poll();
            if (visited[now] > 2) {
                continue;
            }
            for (int i = 2; i < graph[0].length; i++) {
                if (visited[i] != 0 || graph[now][i] == 0) {
                    continue;
                }

                visited[i] = visited[now] + 1;
                qu.offer(i);
                cnt++;
            }
        }

        return cnt;
    }
}
