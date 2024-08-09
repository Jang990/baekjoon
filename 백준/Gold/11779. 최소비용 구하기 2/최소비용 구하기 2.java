import java.io.*;
import java.util.*;

public class Main {
    static List<Edge>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int city = Integer.parseInt(br.readLine());
        int bus = Integer.parseInt(br.readLine());

        initGraph(br, city, bus);

        String[] line = br.readLine().split(" ");
        br.close();

        int start = Integer.parseInt(line[0]);
        int end = Integer.parseInt(line[1]);

        int[] min = initMin(city);
        int[] route = initRoute(city);

        searchMin(min, route, start);
        Route result = searchRoute(route, start, end);
        System.out.println(createResultString(result, min[end]));
    }

    private static int[] initMin(int city) {
        int[] min = new int[city + 1];
        Arrays.fill(min, Integer.MAX_VALUE);
        min[0] = 0;
        return min;
    }

    private static int[] initRoute(int city) {
        int[] route = new int[city + 1];
        for (int i = 1; i < route.length; i++) {
            route[i] = i;
        }
        return route;
    }

    private static String createResultString(Route result, int min) {
        StringBuilder sb = new StringBuilder();
        sb.append(min).append("\n");
        sb.append(result.getCityCnt()).append("\n");
        sb.append(result.getRoute()).append("\n");
        return sb.toString();
    }

    private static Route searchRoute(int[] route, int start, int end) {
        Route result = new Route();
        result.addRoute(end);
        while (true) {
            int nextRoute = route[result.getCurrentCity()];
            result.addRoute(nextRoute);
            if(nextRoute == start)
                break;
        }
        return result;
    }

    private static void searchMin(int[] min, int[] route, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));
        boolean[] visited = new boolean[min.length];
        min[start] = 0;
        pq.offer(new Edge(0, start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if(visited[current.end])
                continue;
            visited[current.end] = true;
            if(current.end != start)
                route[current.end] = current.start;
            for (Edge next : graph[current.end]) {
                int nextWeight = current.weight + next.weight;
                if(min[next.end] <= nextWeight)
                    continue;
                min[next.end] = nextWeight;
                pq.offer(new Edge(next.start, next.end, nextWeight));
            }
        }
    }

    private static void initGraph(BufferedReader br, int city, int bus) throws IOException {
        graph = new List[city + 1];
        for (int i = 1; i <= city; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < bus; i++) {
            String[] line = br.readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            graph[start].add(new Edge(start, end, weight));
        }
    }

    static class Route {
        private int cityCnt;
        private List<Integer> route;

        public Route() {
            this.cityCnt = 0;
            route = new LinkedList<>();
        }

        public void addRoute(int city) {
            route.add(city);
            cityCnt++;
        }

        public int getCityCnt() {
            return cityCnt;
        }

        public String getRoute() {
            StringBuilder sb = new StringBuilder();
            for (int i = route.size() - 1; i >= 0; i--) {
                sb.append(route.get(i)).append(" ");
            }
            return sb.toString();
        }

        public int getCurrentCity() {
            if(route.isEmpty())
                return 0;
            return route.get(route.size() - 1);
        }
    }

    static class Edge {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }
    }
}
