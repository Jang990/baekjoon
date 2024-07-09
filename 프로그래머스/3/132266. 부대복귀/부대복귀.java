import java.util.*;

class Solution {
    List<Integer>[] graph;
    int[] min;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        min = new int[n+1];
        Arrays.fill(min, Integer.MAX_VALUE);
        initGraph(n, roads);
        search(destination);

        int[] result = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            result[i] = min[sources[i]];
            if(result[i] == Integer.MAX_VALUE)
                result[i] = -1;
        }

        return result;
    }

    private void initGraph(int n, int[][] roads) {
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        
        for (int[] road : roads) {
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }
    }

    private void search(int destination) {
        Queue<Integer> qu = new LinkedList<>();
        min[destination] = 0;
        qu.offer(destination);

        while (!qu.isEmpty()) {
            int now = qu.poll();
            for (int next : graph[now]) {
                if(min[next] != Integer.MAX_VALUE && min[next] <= min[now] + 1)
                    continue;
                min[next] = min[now] + 1;
                qu.offer(next);
            }
        }
    }
}