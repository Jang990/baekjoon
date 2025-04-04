import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Edge>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line.split(" ")[0]);
        int M = Integer.parseInt(line.split(" ")[1]);
        graph = new List[N + 1];
        for (int i = 0; i <= N; i++)
            graph[i] =  new LinkedList<>();

        for (int i = 0; i < M; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[arr[0]].add(new Edge(arr[0], arr[1], arr[2]));
            graph[arr[1]].add(new Edge(arr[1], arr[0], arr[2]));
        }
        br.close();

        System.out.println(dijkstra(1));
    }

    private static int dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getW));
        pq.offer(new Edge(0, start, 0));
        int[] min = new int[graph.length];
        Arrays.fill(min, Integer.MAX_VALUE);
        boolean[] visited = new boolean[graph.length];

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if(visited[current.end])
                continue;
            visited[current.end] = true;
            min[current.end] = current.w;
            for (Edge next : graph[current.end]) {
                int nextW = current.w + next.w;
                if(visited[next.end] || min[next.end] <= nextW)
                    continue;
                pq.offer(new Edge(next.start, next.end, nextW));
            }
        }
        return min[graph.length - 1];
    }

    static class Edge {
        int start, end, w;

        public int getW() {
            return w;
        }

        public Edge(int start, int end, int w) {
            this.start = start;
            this.end = end;
            this.w = w;
        }
    }
}
