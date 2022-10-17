import java.io.*;
import java.util.*;

public class Main {
	
	static int T, H, W;
	static char[][] map;
	static boolean[][] visited;
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String line;
		int cnt;
		
		T = Integer.parseInt(br.readLine());
		
		while(T != 0) {
			cnt = 0;
			st = new StringTokenizer(br.readLine(), " ");
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			visited = new boolean[H][W];
			for (int i = 0; i < H; i++) {
				line = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if(!visited[i][j] && map[i][j] == '#') {
						BFS(j, i);
						cnt++;
					}
				}
			}
			sb.append(cnt + "\n");
			T--;
		}
		
		System.out.println(sb);
		
		br.close();
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static void BFS(int x, int y) {
		Queue<Node> qu = new LinkedList<>();
		qu.offer(new Node(x,y));
		
		int[] dirX = {1, -1, 0, 0};
		int[] dirY = {0, 0, 1, -1};
		int nowX, nowY;
		while(!qu.isEmpty()) {
			Node node = qu.poll();
			
			for (int i = 0; i < 4; i++) {
				nowX = node.x + dirX[i];
				nowY = node.y + dirY[i];
				
				if(rangeCheck(nowX, nowY) && !visited[nowY][nowX] && map[nowY][nowX] == '#') {
					qu.offer(new Node(nowX, nowY));
					visited[nowY][nowX] = true;
				}
			}
		}
	}

	private static boolean rangeCheck(int nowX, int nowY) {
		return (nowX < W && nowX >= 0 && nowY < H && nowY >= 0);
	}

}
