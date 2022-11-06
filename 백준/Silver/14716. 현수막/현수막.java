import java.util.*;
import java.io.*;

public class Main {

	static int Y,X;
	static int[][] graph;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y = Integer.valueOf(st.nextToken());
		X = Integer.valueOf(st.nextToken());
		
		graph = new int[Y][X];
		visited = new boolean[Y][X];
		
		for (int i = 0; i < Y; i++) {
			graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray(); 
		}
		
		int cnt = 0;
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if(!visited[i][j] && graph[i][j] == 1) {
					BFS(new Node(j,i));
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		
		br.close();
	}
	
	private static void BFS(Node node) {
		Queue<Node> qu = new LinkedList<>();
		qu.offer(node);
		visited[node.y][node.x] = true;
		
		Node no;
		int[] dirX = {-1,0,1,-1,1,-1,0,1};
		int[] dirY = {1,1,1,0,0,-1,-1,-1};
		int nowX,nowY;
		while(!qu.isEmpty()) {
			no = qu.poll();
			for (int i = 0; i < dirY.length; i++) {
				nowX = no.x+dirX[i];
				nowY = no.y+dirY[i];
				
				if(rangeCheck(nowX,nowY) && !visited[nowY][nowX] && graph[nowY][nowX] == 1) {
					visited[nowY][nowX] = true;
					qu.offer(new Node(nowX, nowY));
				}
			}
		}
	}

	private static boolean rangeCheck(int nowX, int nowY) {
		return (X > nowX && nowX >= 0 && Y > nowY && nowY >= 0);
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}