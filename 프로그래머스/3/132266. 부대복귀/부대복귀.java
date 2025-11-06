import java.util.*;
class Solution {
    int min[];
    List<Integer>[] graph;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        graph = new List[n + 1];
        for(int i = 0; i <= n; i++)
            graph[i] = new ArrayList<Integer>();
        for(int i = 0; i < roads.length; i++) {
            int n1 = roads[i][0];
            int n2 = roads[i][1];
            graph[n1].add(n2);
            graph[n2].add(n1);
        }
        
        min = new int[n + 1];
        Arrays.fill(min, Integer.MAX_VALUE);
        search(destination);
        
        int[] answer = new int[sources.length];
        for(int i = 0; i < answer.length; i++)
            answer[i] = min[sources[i]] == Integer.MAX_VALUE ? -1 : min[sources[i]];
        return answer;
    }
    
    void search(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.w));
        pq.offer(new Edge(0, start, 0));
        
        while(!pq.isEmpty()) {
            Edge current = pq.poll();
            if(current.w >= min[current.e])
                continue;
            min[current.e] = current.w;
            
            int currentLoc = current.e;
            for(Integer next : graph[currentLoc]) {
                if(min[next] <= current.w)
                    continue;
                pq.offer(new Edge(currentLoc, next, min[currentLoc] + 1));
            }
        }
    }
    
    static class Edge {
        int s,e,w;
        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
}