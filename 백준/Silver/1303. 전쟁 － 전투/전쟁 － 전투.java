import java.io.*;
import java.util.*;

public class Main {
	
	static char[][] graph;
	static boolean[][] visited;
	static int N,M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int white = 0 , blue = 0;
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		
		graph = new char[M][N];
		visited = new boolean[M][N];
		
		String str;
		for (int i = 0; i < M; i++) {
			str = br.readLine();
			for (int j = 0; j < N; j++) {
				graph[i][j] = str.charAt(j);
			}
		}
		
		char team;
		int cnt;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					team = graph[i][j];
					cnt = BFS(new Node(j, i), team);
					if(team == 'W') {
						white += Math.pow(cnt, 2);
					}
					else {
						blue += Math.pow(cnt, 2); 
					}
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append(white + " " + blue);
		System.out.println(sb);
		
		br.close();
	}
	
	private static int BFS(Node node, char team) {
		Queue<Node> qu = new LinkedList<>();
		visited[node.y][node.x] = true;
		qu.offer(new Node(node.x, node.y));
		int cnt = 1;
		
		int[] dirX = {0,0,1,-1};
		int[] dirY = {1,-1,0,0};
		int nowX, nowY;
		Node no;
		while(!qu.isEmpty()) {
			no = qu.poll();
			for (int i = 0; i < dirY.length; i++) {
				nowX = no.x + dirX[i];
				nowY = no.y + dirY[i];
				if(rangeCheck(nowX, nowY) && !visited[nowY][nowX] && graph[nowY][nowX] == team) {
					visited[nowY][nowX] = true;
					qu.offer(new Node(nowX, nowY));
					cnt++;
				}
			}
		}
		
		return cnt;
	}

	private static boolean rangeCheck(int nowX, int nowY) {
		return (N > nowX && nowX >= 0 && M > nowY && nowY >= 0);
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}

}