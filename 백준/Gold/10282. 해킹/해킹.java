import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            int[] line = readLine(br);
            int computerCnt = line[0];
            int dependencyCnt = line[1];
            int start = line[2];

            List<Edge>[] graph = initDependencyGraph(br, computerCnt, dependencyCnt);
            int[] min = search(graph, start);
            appendResult(sb, min);
        }
        br.close();

        System.out.println(sb);
    }

    private static void appendResult(StringBuilder sb, int[] min) {
        sb.append(findVisitedComputer(min))
                .append(" ")
                .append(findMaxTime(min))
                .append("\n");
    }

    private static int findMaxTime(int[] min) {
        int result = 0;
        for (int i : min) {
            if(i == Integer.MAX_VALUE)
                continue;
            result = Math.max(result, i);
        }
        return result;
    }

    private static long findVisitedComputer(int[] min) {
        return Arrays.stream(min)
                .filter((n) -> n != Integer.MAX_VALUE)
                .count();
    }

    private static int[] search(List<Edge>[] graph, int start) {
        int[] min = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        Arrays.fill(min, Integer.MAX_VALUE);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(Edge::getW));
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if(visited[current.v])
                continue;
            visited[current.v] = true;
            min[current.v] = current.w;
            for (Edge next : graph[current.v]) {
                int nextWeight = current.w + next.w;
                if(visited[next.v] || min[next.v] <= nextWeight)
                    continue;
                pq.offer(new Edge(next.v, nextWeight));
            }
        }

        return min;
    }

    private static List<Edge>[] initDependencyGraph(BufferedReader br, int computerCnt, int dependencyCnt) throws IOException {
        List<Edge>[] graph = new List[computerCnt + 1];
        for (int i = 1; i <= computerCnt; i++) {
            graph[i] = new LinkedList<>();
        }

        while (dependencyCnt-- > 0) {
            int[] line = readLine(br);
            graph[line[1]].add(new Edge(line[0], line[2]));
        }
        return graph;
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
    }

    static class Edge {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public int getW() {
            return w;
        }
    }
}
