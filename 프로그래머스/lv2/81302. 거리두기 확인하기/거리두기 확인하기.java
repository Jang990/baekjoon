import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int[] solution(String[][] places) {
		int[] answer = new int[places.length];
		char[][] graph;
		for (int i = 0; i < places.length; i++) {
			graph = new char[5][5];
			for (int j = 0; j < places[i].length; j++) {
				for (int k = 0; k < 5; k++) {
					graph[j][k] = places[i][j].charAt(k);
				}
			}
			answer[i] = checkGraph(graph);
		}

		return answer;
	}

	private int checkGraph(char[][] graph) {
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if(graph[i][j] != 'P') 
					continue;
				
				if(BFS(new Node(j, i), graph)) 
					continue;
				else
					return 0;
			}
		}
		
		return 1;
	}
	
	private boolean BFS(Node node, char[][] graph) {
		Queue<Node> qu = new LinkedList<>();
		qu.offer(node);
		
		int[] dirX = {0,0,1,-1};
		int[] dirY = {1,-1,0,0};
		int nowX, nowY;
		while(!qu.isEmpty()) {
			Node no = qu.poll();
			for (int k = 0; k < dirY.length; k++) {
				nowX = dirX[k] + no.x;
				nowY = dirY[k] + no.y;
				
				
				if((node.x == nowX && node.y == nowY) || !rangeCheck(nowX, nowY)) 
					continue;
				
				int d = Math.abs(nowX - node.x) + Math.abs(nowY - node.y);
				
				if(graph[nowY][nowX] == 'P' && d <= 2)
					return false;
				if(graph[nowY][nowX] == 'O' && d < 2)
					qu.offer(new Node(nowX, nowY));
				
			}
		}
		
		return true;
	}

	class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	private boolean rangeCheck(int nowX, int nowY) {
		return (0 <= nowX && nowX < 5 && 0 <= nowY && nowY < 5);
	}
	
}