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
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int e = Integer.parseInt(line[1]);
        edges = new List[n+1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            line = br.readLine().split(" ");
            int n1 = Integer.parseInt(line[0]);
            int n2 = Integer.parseInt(line[1]);
            int w = Integer.parseInt(line[2]);
            edges[n1].add(new Edge(n2, w));
            edges[n2].add(new Edge(n1, w));
        }

        line = br.readLine().split(" ");
        int middle1 = Integer.parseInt(line[0]);
        int middle2 = Integer.parseInt(line[1]);
        br.close();

        int start = 1;
        int end = n;

        stra(start, n);
        int startToMiddle1 = min[middle1];
        int startToMiddle2 = min[middle2];

        stra(end, n);
        int middle1ToEnd = min[middle1];
        int middle2ToEnd = min[middle2];

        int way1 = Integer.MAX_VALUE, way2 = Integer.MAX_VALUE;
        if (startToMiddle1 != Integer.MAX_VALUE && middle2ToEnd != Integer.MAX_VALUE) {
            way1 = startToMiddle1 + middle2ToEnd;
        }
        if (startToMiddle2 != Integer.MAX_VALUE && middle1ToEnd != Integer.MAX_VALUE) {
            way2 = startToMiddle2 + middle1ToEnd;
        }
        int minToMiddleTotalWeight = Math.min(way1, way2);

        stra(middle1, n);
        int middleToMiddle = min[middle2];
        if (minToMiddleTotalWeight == Integer.MAX_VALUE || middleToMiddle == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(minToMiddleTotalWeight + middleToMiddle);
    }

    static void stra(int start, int nodeCnt) {
        min = new int[nodeCnt + 1];
        boolean[] visited = new boolean[nodeCnt + 1];
        Arrays.fill(min, Integer.MAX_VALUE);
        min[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(
                (e1, e2) -> {
                    if(e1.weight < e2.weight)
                        return -1;
                    else
                        return 1;
                }
        );
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if(visited[now.node])
                continue;
            visited[now.node] = true;
            for (Edge next : edges[now.node]) {
                int nextWeight = min[now.node] + next.weight;
                if(min[next.node] <= nextWeight)
                    continue;

                min[next.node] = nextWeight;
                pq.offer(new Edge(next.node, nextWeight));
            }
        }
    }

    static class Edge {
        int node, weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
