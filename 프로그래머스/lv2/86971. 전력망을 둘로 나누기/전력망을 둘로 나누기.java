import java.util.LinkedList;
import java.util.Queue;
class Solution {
    int[][] graph;
	public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        graph = new int[n+1][n+1];
        
        for (int i = 0; i < wires.length; i++) {
			graph[wires[i][0]][wires[i][1]] = 1;
			graph[wires[i][1]][wires[i][0]] = 1;
		}
        
        for (int i = 0; i < wires.length; i++) {
        	graph[wires[i][0]][wires[i][1]] = 0;
			graph[wires[i][1]][wires[i][0]] = 0;
			
			int cnt = BFS(1);
			answer = Math.min(answer, (int)Math.abs((n-cnt) - cnt));
			
			
			graph[wires[i][0]][wires[i][1]] = 1;
			graph[wires[i][1]][wires[i][0]] = 1;
        }
        
        
        return answer;
    }
	private int BFS(int idx) {
		boolean[] visited = new boolean[graph.length];
		Queue<Integer> qu = new LinkedList<>();
		qu.offer(idx);
		visited[idx] = true;
		int cnt = 1;
		
		
		while(!qu.isEmpty()) {
			int num = qu.poll();
			for (int i = 1; i < graph[num].length; i++) {
				if(!visited[i] && graph[num][i] == 1) {
					qu.offer(i);
					visited[i] = true;
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}