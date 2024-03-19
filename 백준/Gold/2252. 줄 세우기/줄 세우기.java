import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static List<Integer>[] graph;
    static int[] enter;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        graph = new List[n+1];
        enter = new int[n+1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            line = br.readLine().split(" ");
            int n1 = Integer.parseInt(line[0]);
            int n2 = Integer.parseInt(line[1]);
            graph[n1].add(n2);
            enter[n2]++;
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        sort(sb);
        System.out.println(sb);
    }

    private static void sort(StringBuilder sb) {
        Queue<Integer> qu = new LinkedList<>();
        for (int i = 1; i < enter.length; i++) {
            for (int j = 1; j < enter.length; j++) {
                if (!visited[j] && enter[j] == 0) {
                    qu.offer(j);
                    visited[j] = true;
                }
            }

            while (!qu.isEmpty()) {
                int now = qu.poll();
                sb.append(now).append(" ");
                List<Integer> list = graph[now];
                for (int next : list) {
                    enter[next]--;
                }
            }
        }
    }
}
