import java.util.*;

public class Main {
	static int[][] graph;
	static boolean[][] visited;
	static int x, y;
	static boolean flag;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		y = sc.nextInt();
		x = sc.nextInt();
		sc.nextLine();
		
		graph = new int[y][x];
		visited = new boolean[y][x];
		flag = false;
		
		String str;
		for (int i = 0; i < graph.length; i++) {
			str = sc.nextLine();
			for (int j = 0; j < graph[i].length; j++) {
				graph[i][j] = (int)(str.charAt(j)-'0');
			}
		}
		sc.close();
		
		for (int i = 0; i < graph[0].length; i++) {
			if(!visited[0][i] && graph[0][i] == 0) {
				if(BFS(i,0)) {
					System.out.println("YES");
					return;
				}
			}
		}
		System.out.println("NO");
		
	}

	static class Node{
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static boolean BFS(int startX, int startY) {
		Queue<Node> qu = new LinkedList<>();
		qu.offer(new Node(startX, startY));
		visited[startY][startX] = true;
		
		int[] dirX = {0,0,1,-1};
		int[] dirY = {1,-1,0,0};
		int nowX, nowY;
		while(!qu.isEmpty()) {
			Node node = qu.poll();
			
			for (int i = 0; i < dirY.length; i++) {
				nowX = node.x + dirX[i];
				nowY = node.y + dirY[i];
				
				if(rangeCheck(nowX, nowY) &&!visited[nowY][nowX] && graph[nowY][nowX] == 0) {
					if(nowY == graph.length-1) {
						return true;
					}
					
					qu.offer(new Node(nowX, nowY));
					visited[nowY][nowX] = true;
				}
			}
			
		}
		
		return false;
	}

	private static boolean rangeCheck(int nowX, int nowY) {
		return (0 <= nowX && nowX < graph[0].length && 0 <= nowY && nowY < graph.length);
	}
}