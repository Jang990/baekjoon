import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int studentCnt = Integer.parseInt(line[0]);
        int diffCnt = Integer.parseInt(line[1]);

        int[] in = new int[studentCnt + 1];
        int[] out = new int[studentCnt + 1];

        List<Integer>[] inGraph = new List[studentCnt + 1];
        List<Integer>[] outGraph = new List[studentCnt + 1];
        for (int i = 1; i <= studentCnt; i++) {
            inGraph[i] = new LinkedList<>();
            outGraph[i] = new LinkedList<>();
        }

        while (diffCnt-- > 0) {
            line = br.readLine().split(" ");
            int small = Integer.parseInt(line[0]);
            int big = Integer.parseInt(line[1]);
            inGraph[big].add(small);
            outGraph[small].add(big);
        }

        br.close();

        int result = 0;
        for (int i = 1; i <= studentCnt; i++) {
            in[i] = calc(inGraph,studentCnt, i);
            out[i] = calc(outGraph,studentCnt, i);
            if((in[i] + out[i] + 1) == studentCnt)
                result++;
        }

        System.out.println(result);
    }

    private static int calc(List<Integer>[] graph, int studentCnt, int start) {
        boolean[] visited = new boolean[studentCnt + 1];
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(start);
        visited[start] = true;

        int result = 0;
        while (!qu.isEmpty()) {
            int now = qu.poll();
            for (int next : graph[now]) {
                if(visited[next]) continue;
                result++;
                qu.offer(next);
                visited[next] =  true;
            }
        }

        return result;
    }
}
