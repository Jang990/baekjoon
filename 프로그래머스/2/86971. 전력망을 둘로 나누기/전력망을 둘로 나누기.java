import java.util.LinkedList;
import java.util.Queue;
class Solution {
    static int[][] graph;
    public static int solution(int n, int[][] wires) {
        graph = new int[n+1][n + 1];

        for (int[] wire : wires) {
            graph[wire[0]][wire[1]] = 1;
            graph[wire[1]][wire[0]] = 1;
        }

        int result = n;
        for (int[] wire : wires) {
            graph[wire[0]][wire[1]] = 0;
            graph[wire[1]][wire[0]] = 0;
            int relatedTowerCnt = bfs(n, wire[0]);
            result = Math.min(result, Math.abs(n - relatedTowerCnt * 2));
            graph[wire[0]][wire[1]] = 1;
            graph[wire[1]][wire[0]] = 1;
        }

        return result;
    }

    private static int bfs(int size, int start) {
        Queue<Integer> qu = new LinkedList<>();
        boolean[] visited = new boolean[size + 1];
        visited[start] = true;
        qu.offer(start);

        int result = 1;
        while (!qu.isEmpty()) {
            int now = qu.poll();
            for (int i = 1; i < graph[now].length; i++) {
                if(visited[i] || graph[now][i] == 0) continue;
                qu.offer(i);
                visited[i] = true;
                result++;
            }
        }
        return result;
    }
}