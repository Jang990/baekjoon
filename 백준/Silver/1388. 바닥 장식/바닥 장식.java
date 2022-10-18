import java.util.*;
import java.io.*;

public class Main {
	
	static int n, m;
	static char[][] graph;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new char[n][m];
		visited = new boolean[n][m];
		String s;
		for (int i = 0; i < graph.length; i++) {
			s = br.readLine();
			for (int j = 0; j < graph[i].length; j++) {
				graph[i][j] = s.charAt(j);
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if(!visited[i][j]) {
					BFS(j, i, graph[i][j]);
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		br.close();
	}
	
	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static void BFS(int x, int y, char c) {
		Queue<Node> qu = new LinkedList<>();
		qu.offer(new Node(x, y));
		visited[y][x] = true;
		Node node;
		
		int[] dirX;
		int[] dirY;
		
		if(c == '-') {
			dirX = new int[]{1, -1};
			dirY = new int[]{0, 0};
		}
		else {
			dirX = new int[]{0, 0};
			dirY = new int[]{1, -1};
		}
		
		int nowX, nowY;
		while(!qu.isEmpty()) {
			node = qu.poll();
			for (int i = 0; i < dirY.length; i++) {
				nowX = node.x + dirX[i];
				nowY = node.y + dirY[i];
				if(rangeCheck(nowX, nowY) && !visited[nowY][nowX] && graph[nowY][nowX] == c) {
					visited[nowY][nowX] = true;
					qu.offer(new Node(nowX, nowY));
				}
			}
		}
	}

	private static boolean rangeCheck(int x, int y) {
		return (x >= 0 && x < m && y >= 0 && y < n);
	}

}