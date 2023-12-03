import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Integer, List<Edge>> edges = new HashMap<>();
    private static int videoCnt;
    private static int questionCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        videoCnt = Integer.parseInt(st.nextToken());
        questionCnt = Integer.parseInt(st.nextToken());
        getEdge(br);
        System.out.println(solveQuestion(br));
        br.close();
    }

    private static String solveQuestion(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < questionCnt; i++) {
            String[] arr = br.readLine().split(" ");
            final int Min_USADO = Integer.parseInt(arr[0]);
            int video = Integer.parseInt(arr[1]);
            int result = solve(Min_USADO, video);
            sb.append(result).append("\n");
        }
        return sb.toString();
    }

    private static int solve(final int Min_USADO, int startVideo) {
        int[] usado = new int[videoCnt + 1];
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(startVideo);
        usado[startVideo] = Integer.MAX_VALUE;

        while (!qu.isEmpty()) {
            int now = qu.poll();
            List<Edge> list = edges.get(now);
            for (Edge edge : list) {
                int minWeight = Math.min(usado[now], edge.weight);
                if(usado[edge.node] != 0)
                    continue;

                usado[edge.node] = minWeight;
                qu.offer(edge.node);
            }
        }

        int result = 0;
        for (int i = 0; i < usado.length; i++) {
            if(i == startVideo)
                continue;
            if(usado[i] >= Min_USADO)
                result++;
        }

        return result;
    }

    private static void getEdge(BufferedReader br) throws IOException {
        for (int i = 0; i < videoCnt-1; i++) {
            String[] arr = br.readLine().split(" ");
            int n1 = Integer.parseInt(arr[0]);
            int n2 = Integer.parseInt(arr[1]);
            int weight = Integer.parseInt(arr[2]);
            addEdge(n1, n2, weight);
            addEdge(n2, n1, weight);
        }
    }

    private static void addEdge(int n1, int n2, int weight) {
        List<Edge> list = edges.get(n1);
        if(list == null)
            list = new ArrayList<>();

        list.add(new Edge(n2, weight));
        edges.put(n1, list);
    }

    static class Edge {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
