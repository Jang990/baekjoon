import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static List<Edge>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int[] line = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[line[0]].add(new Edge(line[1], line[2]));
            graph[line[1]].add(new Edge(line[0], line[2]));
        }
        br.close();

        long[] distance = calculateDistance(1);
        System.out.println(Arrays.stream(distance).max().getAsLong() - 1);
    }

    private static long[] calculateDistance(int start) {
        long[] distance = new long[graph.length];
        Queue<Edge> qu = new LinkedList<>();
        qu.offer(new Edge(start, 0));
        distance[start] = 1;

        while (!qu.isEmpty()) {
            Edge current = qu.poll();
            for (Edge next : graph[current.e]) {
                if(distance[next.e] != 0)
                    continue;
                qu.offer(next);
                distance[next.e] = distance[current.e] + next.w;
            }
        }


        return distance;
    }

    static class Edge {
        int e, w;

        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
}
