import java.util.*;

class Solution {
    List<Integer>[] graph;
    int dest;
    int[] min;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        dest = destination;
        int[] answer = new int[sources.length];
        graph = new List[n+1];
        min = new int[n+1];
        Arrays.fill(min, Integer.MAX_VALUE);
        for(int i = 0; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        for(int i = 0; i < roads.length; i++) {
            int n1 = roads[i][0];
            int n2 = roads[i][1];
            graph[n1].add(n2);
            graph[n2].add(n1);
            /*graph[n1].add(new Edge(n1, n2));
            graph[n2].add(new Edge(n2, n1));*/
        }
        
        dijkstra(n, dest);
        
        for(int i = 0; i < sources.length; i++) {
            answer[i] = min[sources[i]];
            if(answer[i] == Integer.MAX_VALUE) 
                answer[i] = -1;                
        }
        
        return answer;
    }
    
    void dijkstra(int size, int start) {
        Queue<Integer> qu = new LinkedList();
        qu.offer(start);
        
        boolean[] visited = new boolean[size+1];
        min[start] = 0;
        
        while(!qu.isEmpty()) {
            int now = qu.poll();
            if(visited[now]) continue;
            visited[now] = true;
            for(int next : graph[now]) {
                if(min[now] + 1 >= min[next])
                    continue;
                min[next] = min[now] + 1;
                qu.offer(next);
            }
        }
    } 
}