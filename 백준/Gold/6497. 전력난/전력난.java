import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int[] line = readLine(br);
            int houseCnt = line[0];
            int roadCnt = line[1];
            if(houseCnt == 0 && roadCnt == 0)
                break;
            parent = new int[houseCnt];
            for (int i = 0; i < houseCnt; i++)
                parent[i] = i;
            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getW));
            int sum = 0;
            for (int i = 0; i < roadCnt; i++) {
                line = readLine(br);
                int weight = line[2];
                sum += weight;
                pq.offer(new Edge(line[0], line[1], weight));
            }
            sb.append(sum - getMin(pq)).append("\n");
        }
        br.close();
        System.out.println(sb);
    }

    private static int getMin(PriorityQueue<Edge> pq) {
        int result = 0;
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if(isSameParent(current.s, current.e))
                continue;
            result += current.w;
            union(current.s, current.e);
        }
        return result;
    }

    private static boolean isSameParent(int n1, int n2) {
        int n1Parent = findParent(n1);
        int n2Parent = findParent(n2);
        return n1Parent == n2Parent;
    }

    private static int findParent(int child) {
        if(parent[child] == child)
            return child;
        return findParent(parent[child]);
    }

    private static void union(int n1, int n2) {
        int n1Parent = findParent(n1);
        int n2Parent = findParent(n2);

        if(n1Parent == n2Parent)
            return;
        if(n1Parent > n2Parent)
            parent[n1Parent] = n2Parent;
        else
            parent[n2Parent] = n1Parent;
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    static class Edge {
        int s,e, w;

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
