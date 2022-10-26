import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] arr;
	static boolean[][] visited;
	static int MaxHeight, Max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.valueOf(br.readLine());
		arr = new int[N][N];
		Max = 1;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.valueOf(st.nextToken());
				MaxHeight = Math.max(arr[i][j], MaxHeight);
			}
		}
		
		int cnt;
		for (int i = 1; i < MaxHeight; i++) {
			cnt = 0;
			visited = new boolean[N][N];
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if(!visited[j][k] && arr[j][k] > i) {
						BFS(k,j,i);
						cnt++;
					}
				}
			}
			Max = Math.max(cnt, Max);
		}
		
		System.out.println(Max);
		
		br.close();
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static void BFS(int x, int y, int height) {
		Queue<Node> qu = new LinkedList<>();
		qu.offer(new Node(x,y));
		visited[y][x] = true;
		
		int[] dirX = {0,0,1,-1};
		int[] dirY = {1,-1,0,0};
		
		Node n;
		int nowX, nowY;
		while(!qu.isEmpty()) {
			n = qu.poll();
			for (int i = 0; i < dirY.length; i++) {
				nowX = n.x + dirX[i];
				nowY = n.y + dirY[i];
				if(rangeCheck(nowX,nowY) && !visited[nowY][nowX] && arr[nowY][nowX] > height) {
					qu.offer(new Node(nowX, nowY));
					visited[nowY][nowX] = true;
				}
			}
		}
	}

	private static boolean rangeCheck(int nowX, int nowY) {
		return (N > nowX && nowX >= 0 && N > nowY && nowY >= 0);
	}

}
