import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// big network 예시 그림에서 초록색 말고 파란색에서 탐색하면 small network로 결과가 나올 위험이 있음

public class Main {

    private static int nodeCnt;
    private static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = readLine(br);
        nodeCnt = input[0];
        int edgeCnt = input[1];

        graph = new List[nodeCnt + 1];
        for (int i = 0; i <= nodeCnt; i++)
            graph[i] = new LinkedList<>();

        for (int i = 0; i < edgeCnt; i++) {
            input = readLine(br);
            graph[input[0]].add(input[1]);
            graph[input[1]].add(input[0]);
        }
        br.close();

        for (int start = 1; start <= nodeCnt; start++) {
            int[] visited = search(start);

            if (isBigNetwork(visited)) {
                System.out.println("Big World!");
                return;
            }
        }

        System.out.println("Small World!");
    }

    private static int[] search(int start) {
        int[] visited = new int[nodeCnt + 1];
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(start);
        visited[start] = 1;
        while (!qu.isEmpty()) {
            int current = qu.poll();
            for (int next : graph[current]) {
                if(visited[next] != 0 && visited[next] <= visited[current] + 1)
                    continue;
                visited[next] = visited[current] + 1;
                qu.offer(next);
            }
        }
        return visited;
    }

    private static boolean isBigNetwork(int[] visited) {
        for (int i = 1; i < visited.length; i++) {
            if(visited[i] == 0 || visited[i] > 7)
                return true;
        }
        return false;
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
