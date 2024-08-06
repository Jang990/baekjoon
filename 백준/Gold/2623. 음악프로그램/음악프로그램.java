
import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static int[] in;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        in = new int[n + 1];
        visited = new boolean[n + 1];
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int[] pd = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf).toArray();
            for (int j = 2; j < pd.length; j++) {
                int prev = pd[j - 1];
                int current = pd[j];
                graph[prev].add(current);
                in[current]++;
            }
        }
        br.close();

        String result = search();
        if(hasOnlyZero(in))
            System.out.println(result);
        else
            System.out.println(0);
    }

    private static String search() {
        Queue<Integer> qu = new LinkedList<>();
        qu.addAll(findUnvisitedZero());

        StringBuilder sb = new StringBuilder();
        for (int n : qu) {
            append(sb, n);
            visited[n] = true;
        }

        while (!qu.isEmpty()) {
            int current = qu.poll();
            for (int next : graph[current]) {
                in[next]--;
                if(visited[next] || in[next] != 0)
                    continue;

                qu.offer(next);
                visited[next] = true;
                append(sb, next);
            }
        }

        return sb.toString();
    }

    private static void append(StringBuilder sb, int next) {
        sb.append(next).append("\n");
    }

    private static boolean hasOnlyZero(int[] in) {
        for (int i = 1; i < in.length; i++) {
            if(in[i] != 0)
                return false;
        }
        return true;
    }

    private static List<Integer> findUnvisitedZero() {
        List<Integer> result = new LinkedList<>();
        for (int i = 1; i < in.length; i++) {
            if(isUnvisitedZero(i))
                result.add(i);
        }
        return result;
    }

    private static boolean isUnvisitedZero(int index) {
        return !visited[index] && in[index] == 0;
    }
}
