import java.io.*;
import java.util.*;

public class Main {
	
	private static int N, M, T;
	private static int[][] graph;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		
		graph = new int[N][M];
		visited = new boolean[N][M];
		
		int sum;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				sum = 0;
				for (int k = 0; k < 3; k++) {
					sum += Integer.valueOf(st.nextToken());
				}
				graph[i][j] = sum / 3;
			}
			
		}
		
		T = Integer.valueOf(br.readLine());
		int cnt = 0;
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if(!visited[i][j] && graph[i][j] >= T) {
					BFS(new Node(j,i));
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		
		br.close();
	}
	
	private static void BFS(Node startNode) {
		Queue<Node> qu = new LinkedList<>();
		qu.offer(startNode);
		visited[startNode.y][startNode.x] = true;
		
		Node no;
		int[] dirX = {0,0,1,-1};
		int[] dirY = {1,-1,0,0};
		int nowX, nowY;
		while(!qu.isEmpty()) {
			no = qu.poll();
			for (int i = 0; i < dirY.length; i++) {
				nowX = dirX[i] + no.x;
				nowY = dirY[i] + no.y;
				if(rangeCheck(nowX, nowY) && !visited[nowY][nowX] && graph[nowY][nowX] >= T) {
					qu.offer(new Node(nowX, nowY));
					visited[nowY][nowX] = true;
				}
			}
		}
	}

	private static boolean rangeCheck(int nowX, int nowY) {
		return (N > nowY && nowY >= 0 && M > nowX && nowX >= 0);
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			
			this.x = x;
			this.y = y;
		}
	}
	

}