import java.util.*;
class Solution {
    static List<Edge>[] graph;
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        initGraph(n, fares);

        int[] sTo = search(s);
        int[] aTo = search(a);
        int[] bTo = search(b);

        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if(sTo[i] == Integer.MAX_VALUE
                    || aTo[i] == Integer.MAX_VALUE
                    ||  bTo[i] == Integer.MAX_VALUE)
                continue;
            result = Math.min(result, sTo[i] + aTo[i] + bTo[i]);
        }
        return result;
    }

    private static int[] search(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getW));
        pq.offer(new Edge(start, 0));

        int[] result = new int[graph.length];
        boolean[] visited = new boolean[graph.length];

        Arrays.fill(result, Integer.MAX_VALUE);
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if(visited[current.n])
                continue;
            visited[current.n] = true;
            result[current.n] = current.w;

            for (Edge next : graph[current.n]) {
                int nextWeight = result[current.n] + next.w;
                if(visited[next.n] || result[next.n] <= nextWeight)
                    continue;
                pq.offer(new Edge(next.n, nextWeight));
            }
        }

        return result;
    }

    private static void initGraph(int n, int[][] fares) {
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new LinkedList<>();

        for (int[] fare : fares) {
            graph[fare[0]].add(new Edge(fare[1], fare[2]));
            graph[fare[1]].add(new Edge(fare[0], fare[2]));
        }
    }

    static class Edge {
        int n,w;

        public Edge(int n, int w) {
            this.n = n;
            this.w = w;
        }

        public int getW() {
            return w;
        }
    }
}