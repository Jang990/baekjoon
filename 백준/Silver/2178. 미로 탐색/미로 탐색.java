import java.util.*;
public class Main {
	
	static int N;
	static int M;
	static int[][] maze;
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sc.nextLine();
		
		maze = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = sc.nextLine();
			for (int j = 0; j < str.length(); j++) {
				maze[i][j] = str.charAt(j) - '0';
			}
		}
		
		BFS(0, 0);
		System.out.println(maze[N-1][M-1]);
		
		sc.close();
	}
	
	private static void BFS(int x, int y) {
		Queue<Node> que = new LinkedList<>();
		int[] dirX = {0, 0, 1, -1};
		int[] dirY = {1, -1, 0, 0};
		
		que.offer(new Node(x, y));
		visited[y][x] = true;
		
		while(!que.isEmpty()) {
			Node node = que.poll();
			if(node.x == M-1 && node.y == N-1)
				return;
			
			for (int i = 0; i < 4; i++) {
				int nowX = node.x+ dirX[i];
				int nowY = node.y+ dirY[i];
				
				if(rangeCheck(nowX, nowY) && !visited[nowY][nowX] && maze[nowY][nowX] != 0) {
					visited[nowY][nowX] = true;
					maze[nowY][nowX] = maze[node.y][node.x] + 1;
					que.offer(new Node(nowX, nowY));
				}
			}
			
		}
		
	}

	private static boolean rangeCheck(int nowX, int nowY) {
		return (nowX >= 0 && nowX < M && nowY >= 0 && nowY < N);
	}

	static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
