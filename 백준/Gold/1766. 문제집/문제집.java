import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] enter;
    static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        enter = new int[N+1];
        graph = new List[N+1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            graph[n1].add(n2);
            enter[n2]++;
        }
        br.close();

        System.out.println(sort());
    }

    private static String sort() {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> qu = new PriorityQueue<>();
        for (int i = 1; i < enter.length; i++) {
            if(enter[i] == 0)
                qu.offer(i);
        }

        while (!qu.isEmpty()) {
            int now = qu.poll();
            sb.append(now).append(" ");
            for (int next : graph[now]) {
                enter[next]--;
                if(enter[next] == 0)
                    qu.offer(next);
            }
        }

        return sb.toString();
    }
}
