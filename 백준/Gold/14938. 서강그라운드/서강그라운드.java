import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Edge>[] graph;
    private static int[] regionItemCnt;
    private static int maxLen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = readLine(br);
        int regionCnt = line[0];
        maxLen = line[1];
        int roadCnt = line[2];

        graph = new List[regionCnt + 1];
        for (int i = 1; i <= regionCnt; i++) {
            graph[i] = new ArrayList<>();
        }

        regionItemCnt = readLine(br);
        for (int i = 0; i < roadCnt; i++) {
            line = readLine(br);
            graph[line[0]].add(new Edge(line[0], line[1], line[2]));
            graph[line[1]].add(new Edge(line[1], line[0], line[2]));
        }

        br.close();

        int max = Integer.MIN_VALUE;
        for (int regionIdx = 1; regionIdx <= regionCnt; regionIdx++) {
            max = Math.max(search(regionIdx), max);
        }
        System.out.println(max);
    }

    private static int search(int startRegion) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getW));
        int[] minLen = new int[graph.length];
        Arrays.fill(minLen, Integer.MAX_VALUE);

        pq.offer(new Edge(0, startRegion, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if(minLen[current.e] <= current.w)
                continue;
            minLen[current.e] = current.w;
            for (Edge next : graph[current.e]) {
                int nextRegion = next.e;
                int nextW = current.w + next.w;
                if(minLen[nextRegion] <= nextW)
                    continue;
                pq.offer(new Edge(next.s, nextRegion, nextW));
            }
        }

        int itemSum = 0;
        for (int i = 1; i < minLen.length; i++) {
            if(minLen[i] <= maxLen)
                itemSum += regionItemCnt[i - 1];
        }

        return itemSum;
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    static class Edge {
        int s,e,w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        public int getW() {
            return w;
        }
    }
}
