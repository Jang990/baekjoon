import java.util.*;

class Solution {
    static int max = 0;
    static int[] status;
    static boolean[] visited;
    static List<Integer>[] graph;
    public static int solution(int[] info, int[][] edges) {
        status = info;
        graph = new List[info.length];
        visited = new boolean[info.length];

        for (int i = 0; i < info.length; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }

        visited[0] = true;
        rec(1, 0);
        return max;
    }

    private static void rec(int sheep, int wolf) {
        if(sheep > wolf)
            max = Math.max(max, sheep);

        for (int i = 0; i < visited.length; i++) {
            if(!visited[i]) continue;
            for (int next : graph[i]) {
                if(visited[next]) continue;
                visited[next] = true;
                if(status[next] == 0)
                    rec(sheep + 1, wolf);
                else if(sheep > wolf + 1)
                    rec(sheep, wolf + 1);
                visited[next] = false;
            }
        }
    }
}