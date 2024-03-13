import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static PriorityQueue<Edge> pq;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        pq = new PriorityQueue<>(Comparator.comparing(e1 -> e1.weight));
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(n1, n2, weight));
        }
        br.close();

        int sum = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if(isSameParent(edge.n1, edge.n2))
                continue;

            sum += edge.weight;
            union(edge.n1, edge.n2);
        }

        System.out.println(sum);
    }

    static void union(int n1, int n2) {
        int n1Parent = find(n1);
        int n2Parent = find(n2);

        if(n1Parent < n2Parent)
            parent[n1Parent] = n2Parent;
        else
            parent[n2Parent] = n1Parent;
    }

    static boolean isSameParent(int n1, int n2) {
        return find(n1) == find(n2);
    }

    static int find(int n1) {
        if(parent[n1] == n1) return n1;
        else return parent[n1] = find(parent[n1]);
    }

    static class Edge {
        int n1, n2;
        int weight;

        public Edge(int n1, int n2, int weight) {
            this.n1 = n1;
            this.n2 = n2;
            this.weight = weight;
        }
    }
}
