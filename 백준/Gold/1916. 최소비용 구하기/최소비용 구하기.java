import java.io.*;
import java.util.*;

public class Main {
    static List<Edge>[] cities;
    static int[] min;
    private static int cityCnt, busCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cityCnt = Integer.parseInt(br.readLine());
        busCnt = Integer.parseInt(br.readLine());

        cities = new List[cityCnt + 1];
        for (int i = 1; i <= cityCnt; i++) {
            cities[i] = new LinkedList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < busCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            cities[start].add(new Edge(end, weight));
        }

        st = new StringTokenizer(br.readLine());
        br.close();

        min = new int[cityCnt + 1];
        Arrays.fill(min, Integer.MAX_VALUE);
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        search(start);
        System.out.println(min[end]);
    }

    private static void search(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getW));
        boolean[] visited = new boolean[cityCnt + 1];
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if(visited[now.v]) continue;
            visited[now.v] = true;

            for (Edge next : cities[now.v]) {
                int nextCost = now.w + next.w;
                if(min[next.v] <= nextCost)
                    continue;
                min[next.v] = nextCost;
                pq.offer(new Edge(next.v, nextCost));
            }
        }
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
