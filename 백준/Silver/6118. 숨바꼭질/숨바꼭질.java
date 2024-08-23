import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static List<Integer>[] graph;
    static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = readLine(br);
        int N = parse(line[0]);
        int M = parse(line[1]);

        distance = new int[N + 1];
        initGraph(N);
        readGraph(br, M);

        br.close();

        BFS(1);

        printResult();
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        sb.append(hidingBarn()).append(" ");
        sb.append(distance[hidingBarn()]).append(" ");
        sb.append(countBarnsAtSameDistance(distance[hidingBarn()])).append(" ");
        System.out.println(sb);
    }

    private static int countBarnsAtSameDistance(int base) {
        int result = 0;
        for (int i = 1; i < distance.length; i++) {
            if(distance[i] == base)
                result++;
        }
        return result;
    }

    private static int hidingBarn() {
        int barn = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] > max) {
                max = distance[i];
                barn = i;
            }
        }

        return barn;
    }

    private static void BFS(int start) {
        Queue<Integer> qu = new LinkedList<>();
        qu.addAll(searchNext(start));
        distance[start] = -1;

        while (!qu.isEmpty()) {
            int current = qu.poll();
            qu.addAll(searchNext(current));
        }
    }

    private static List<Integer> searchNext(int start) {
        List<Integer> result = new LinkedList<>();
        for (int next : graph[start]) {
            int nextLen = distance[start] + 1;
            if(distance[next] != 0 && distance[next] <= nextLen)
                continue;
            distance[next] = nextLen;
            result.add(next);
        }
        return result;
    }

    private static void readGraph(BufferedReader br, int M) throws IOException {
        String[] line;
        for (int i = 0; i < M; i++) {
            line = readLine(br);
            int n1 = parse(line[0]);
            int n2 = parse(line[1]);

            linkGraph(n1, n2);
        }
    }

    private static void linkGraph(int n1, int n2) {
        graph[n1].add(n2);
        graph[n2].add(n1);
    }

    private static void initGraph(int n) {
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
    }

    private static String[] readLine(BufferedReader br) throws IOException {
        return br.readLine().split(" ");
    }

    private static int parse(String arg) {
        return Integer.parseInt(arg);
    }
}
