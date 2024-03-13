import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        parent = new int[V + 1];
        pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.add(new Edge(n1, n2, weight));
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

    static boolean isSameParent(int n1, int n2) {
        return find(n1) == find(n2);
    }

    static int find(int n) {
        if(parent[n] == n) return n;
        else return parent[n] = find(parent[n]);
    }

    static void union(int n1, int n2) {
        int n1Parent = find(n1);
        int n2Parent = find(n2);

        if(n1Parent < n2Parent)
            parent[n2Parent] = n1Parent;
        else
            parent[n1Parent] = n2Parent;
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
