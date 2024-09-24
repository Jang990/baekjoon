import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = readArgs(br);
        final int N = arr[0];
        final int robot1 = arr[1];
        final int robot2 = arr[2];

        List<Edge>[] graph = readGraph(br, N);
        br.close();

        int[] min = findMin(graph, robot1);
        System.out.println(min[robot2]);
    }

    private static int[] findMin(List<Edge>[] graph, int start) {
        int[] result = new int[graph.length];
        boolean[] visited = new boolean[graph.length];

        PriorityQueue<Route> pq = new PriorityQueue<>(Comparator.comparing(Route::w));
        pq.offer(new Route(new Edge(start, 0)));
        while (!pq.isEmpty()) {
            Route now = pq.poll();
            if(visited[now.v()])
                continue;
            visited[now.v()] = true;
            result[now.v()] = now.getRouteLength();
            for (Edge next : graph[now.v()]) {
                Edge routeEdge = new Edge(next.v, now.w() + next.w);
                pq.offer(new Route(now, routeEdge, next.w));
            }
        }

        return result;
    }

    private static List<Edge>[] readGraph(BufferedReader br, int n) throws IOException {
        List<Edge>[] result = initGraph(n);

        for (int i = 0; i < n - 1; i++) {
            setGraph(result, readArgs(br));
        }
        return result;
    }

    private static void setGraph(List<Edge>[] graph, int[] input) {
        graph[input[0]].add(new Edge(input[1], input[2]));
        graph[input[1]].add(new Edge(input[0], input[2]));
    }

    private static List<Edge>[] initGraph(int n) {
        List<Edge>[] result = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            result[i] = new LinkedList<>();
        }
        return result;
    }

    private static int[] readArgs(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
    }

    static class Edge {
        public final int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static class Route {
        final Edge edge;
        final int maxWeight;

        public Route(Route prev, Edge next, int nextWeight) {
            this.edge = next;
            maxWeight = Math.max(prev.maxWeight, nextWeight);
        }

        public Route(Edge now) {
            this.edge = now;
            maxWeight = 0;
        }

        public int v() {
            return edge.v;
        }
        public int w() {
            return edge.w;
        }

        public int getRouteLength() {
            return edge.w - maxWeight;
        }
    }

}
