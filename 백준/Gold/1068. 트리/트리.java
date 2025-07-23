import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        graph = new List[N];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }

        int[] nodes = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int root = -1;
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == -1) {
                root = i;
                continue;
            }
            graph[nodes[i]].add(i);
        }
        int start = Integer.parseInt(br.readLine());
        br.close();

        if (nodes[start] == -1) {
            System.out.println(0);
            return;
        }

        graph[nodes[start]].remove(Integer.valueOf(start));
        System.out.println(countLeaf(root));
    }

    private static int countLeaf(int root) {
        Queue<Integer> qu = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        int visitedCnt = 0;
        qu.offer(root);
        visited[root] = true;
        visitedCnt++;

        while (!qu.isEmpty()) {
            int current = qu.poll();
            for (int next : graph[current]) {
                if(visited[next])
                    continue;
                qu.offer(next);
                visited[next] = true;
                visitedCnt++;
            }
        }

        int result = 0;
        for (int i = 0; i < graph.length; i++) {
            if(visited[i] && graph[i].isEmpty() && i != root)
                result++;
        }

        if(result == 0 && visitedCnt == 1)
            return 1;
        
        return result;
    }
}
