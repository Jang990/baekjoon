import java.util.LinkedList;
import java.util.Queue;
class Solution {
    static boolean[] visited;

    public static int solution(int n, int[][] computers) {
        visited = new boolean[n];
        int result = 0;
        for (int i = 0; i < n; i++) {
            if(visited[i])
                continue;
            visit(i, computers);
            result++;
        }
        return result;
    }

    private static void visit(int start, int[][] graph) {
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(start);
        visited[start] = true;

        while (!qu.isEmpty()) {
            int current = qu.poll();
            for(int i = 0; i < graph[current].length; i++) {
                if(graph[current][i] == 0 || visited[i])
                    continue;
                qu.offer(i);
                visited[i] = true;
            }
        }
    }
}