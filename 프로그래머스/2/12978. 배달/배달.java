import java.util.*;

class Solution {
    static List<Edge>[] graph;
    public static int solution(int N, int[][] road, int K) {
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < road.length; i++) {
            graph[road[i][0]].add(new Edge(road[i][1], road[i][2]));
            graph[road[i][1]].add(new Edge(road[i][0], road[i][2]));
        }

        int[] min = searchMin();
        int result = 0;
        for (int i = 1; i < min.length; i++) {
            if(min[i] <= K)
                result++;
        }
        return result;
    }

    private static int[] searchMin() {
        int[] min = new int[graph.length];
        Arrays.fill(min, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getW));
        pq.offer(new Edge(1, 0));
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentNode = current.n;
            if (min[currentNode] <= current.w)
                continue;
            min[currentNode] = current.w;
            for (Edge next : graph[currentNode]) {
                int nextW = min[currentNode] + next.w;
                if(min[next.n] <= nextW)
                    continue;
                pq.offer(new Edge(next.n, nextW));
            }
        }

        return min;
    }

    static class Edge {
        int n, w;

        public Edge(int n, int w) {
            this.n = n;
            this.w = w;
        }

        public int getW() {
            return w;
        }
    }
}