import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    private static int partyPlace;
    private static List<Edge>[] nodes;
    private static int[] min;
    private static int[] minGo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] cond = br.readLine().split(" ");
        int v = Integer.parseInt(cond[0]);
        int e = Integer.parseInt(cond[1]);
        partyPlace = Integer.parseInt(cond[2]);

        nodes = new List[v + 1];
        min = new int[v + 1];
        for (int i = 0; i <= v; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            cond = br.readLine().split(" ");
            int start = Integer.parseInt(cond[0]);
            int end = Integer.parseInt(cond[1]);
            int weight = Integer.parseInt(cond[2]);

            nodes[start].add(new Edge(end, weight));
        }
        br.close();

        min = new int[v + 1];
        Arrays.fill(min, Integer.MAX_VALUE);
        dijkstra(partyPlace, min);

        for (int i = 1; i <= v; i++) {
            if(i == partyPlace) continue;

            minGo = new int[v + 1];
            Arrays.fill(minGo, Integer.MAX_VALUE);
            dijkstra(i, minGo);

            min[i] += minGo[partyPlace];
        }

        int result = Integer.MIN_VALUE;
        for (int i = 1; i <= v; i++) {
            if(i == partyPlace) continue;
            result = Math.max(min[i], result);
        }
        System.out.println(result);

    }

    private static void dijkstra(int start, int[] minCheck) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> {
            if(e1.weight < e2.weight)
                return -1;
            else
                return 1;
        });
        boolean[] visited = new boolean[minCheck.length];
        minCheck[start] = 0;
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if(visited[now.v]) continue;
            visited[now.v] = true;

            List<Edge> list = nodes[now.v];
            for (Edge next : list) {
                int nextWeight = minCheck[now.v] + next.weight;
                if(visited[next.v] || minCheck[next.v] <= nextWeight)
                    continue;
                minCheck[next.v] = nextWeight;
                pq.offer(new Edge(next.v, nextWeight));
            }
        }
    }

    static class Edge {
        int v;
        int weight;

        public Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

}
