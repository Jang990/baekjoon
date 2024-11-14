import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static List<Integer>[] graph;
    static final String YES = "Y";

    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        initGraph();
        for (int i = 0; i < graph.length; i++) {
            int[] visited = new int[graph.length];
            visited[i] = 1;
            visit2Friend(visited, i);
            if(max >= count(visited))
                continue;
            max = count(visited);
        }

        System.out.println(max);
    }

    private static int count(int[] visited) {
        int result = 0;
        for (int val : visited) {
            if(val > 1)
                result++;
        }
        return result;
    }


    private static void visit2Friend(int[] visited, int now) {
        if(visited[now] > 2)
            return;
        for (int next : graph[now]) {
            if(visited[next] != 0 && visited[next] <= visited[now] + 1)
                continue;
            visited[next] = visited[now] + 1;
            visit2Friend(visited, next);
        }
    }

    private static void initGraph() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            String[] yesOrNo = br.readLine().split("");
            for (int j = 0; j < yesOrNo.length; j++) {
                if(yesOrNo[j].equals(YES))
                    graph[i].add(j);
            }
        }
        br.close();
    }
}
