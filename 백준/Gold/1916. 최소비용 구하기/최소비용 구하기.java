import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static List<Edge>[] edges;
    static int[] min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        edges = new List[n + 1];
        min = new int[n + 1];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
        }
        Arrays.fill(min, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            edges[start].add(new Edge(end, weight));
        }

        String[] line = br.readLine().split(" ");
        int start = Integer.parseInt(line[0]);
        int end = Integer.parseInt(line[1]);

        br.close();

        dj(start);
        System.out.println(min[end]);
    }

    private static void dj(int start) {
        min[start] = 0;
        boolean[] visited = new boolean[min.length];
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>((n1, n2) -> {
            if(n1.weight > n2.weight)
                return 1;
            else
                return -1;
        });
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if(visited[now.end])
                continue;
            visited[now.end] = true;
            for (Edge edge : edges[now.end]) {
                int nextWeight = edge.weight + min[now.end];
                if(min[edge.end] <= nextWeight)
                    continue;
                min[edge.end] = nextWeight;
                pq.offer(new Edge(edge.end, nextWeight));
            }
        }
    }

    static class Edge {
        int end;
        int weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}
