import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] visited;
    static int[] min;
    static List<Node>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int nodeCnt = Integer.parseInt(line[0]);
        int edge = Integer.parseInt(line[1]);
        int start = Integer.parseInt(br.readLine());

        visited = new boolean[nodeCnt+1];
        min = new int[nodeCnt+1];
        Arrays.fill(min, Integer.MAX_VALUE);
        initGraph(nodeCnt);

        for (int i = 0; i < edge; i++) {
            line = br.readLine().split(" ");
            int u = Integer.parseInt(line[0]);
            int v = Integer.parseInt(line[1]);
            int w = Integer.parseInt(line[2]);

            graph[u].add(new Node(v, w));
        }
        br.close();

        bfs(start);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < min.length; i++) {
            if (min[i] == Integer.MAX_VALUE) {
                sb.append("INF\n");
            } else {
                sb.append(min[i]).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void bfs(int start) {
        PriorityQueue<Node> qu = new PriorityQueue<>();
        qu.offer(new Node(start, 0));
        min[start] = 0;

        while (!qu.isEmpty()) {
            Node now = qu.poll();
            List<Node> nodes = graph[now.number];

            if(visited[now.number]) continue;
            visited[now.number] = true;
            for (Node next : nodes) {
                int nextWeight = min[now.number] + next.weight;
                if(min[next.number] <= nextWeight)
                    continue;

                min[next.number] = nextWeight;
                qu.offer(new Node(next.number, nextWeight));
            }
        }
    }

    private static void initGraph(int nodeCnt) {
        graph = new List[nodeCnt + 1];
        for (int i = 1; i <= nodeCnt; i++) {
            graph[i] = new LinkedList<>();
        }
    }

    static class Node implements Comparable<Node> {
        int number;
        int weight;

        public Node(int number, int weight) {
            this.number = number;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return weight - n.weight;
        }
    }
}
