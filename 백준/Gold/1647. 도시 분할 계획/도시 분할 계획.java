import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getW));
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = readLine(br);
        int cityCnt = arr[0];
        int roadCnt = arr[1];

        parent = new int[cityCnt + 1];
        for (int i = 0; i <= cityCnt; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < roadCnt; i++) {
            arr = readLine(br);
            pq.offer(new Edge(arr[0], arr[1], arr[2]));
        }

        br.close();

        System.out.println(search());
    }

    private static int search() {
        int maxCost = 0;
        int sum = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if(isSameParent(current.s, current.e))
                continue;
            union(current.s, current.e);
            maxCost = Math.max(maxCost, current.w);
            sum += current.w;
        }

        return sum - maxCost;
    }

    private static boolean isSameParent(int n1, int n2) {
        return findParent(n1) == findParent(n2);
    }

    private static int findParent(int n) {
        if(parent[n] == n)
            return n;
        return findParent(parent[n]);
    }

    private static void union(int n1, int n2) {
        int n1Parent = findParent(n1);
        int n2Parent = findParent(n2);

        if (n1Parent < n2Parent)
            parent[n2Parent] = n1Parent;
        else
            parent[n1Parent] = n2Parent;
    }

    static class Edge {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        public int getW() {
            return w;
        }
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
