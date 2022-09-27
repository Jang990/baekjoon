import java.util.*;

public class Main {

	static int w, h;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();
		int cnt;
		while(true) {
			w = sc.nextInt();
			h = sc.nextInt();

			if(w == 0 && h == 0) 
				break;
			sc.nextLine();
			cnt = 0;
			
			map = new int[h][w];
			visited = new boolean[h][w];
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					map[i][j] = sc.nextInt();
				}
				sc.nextLine();
			}
			
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if(map[i][j] == 1 && !visited[i][j]) {
						DFS(j, i);
						cnt++;
					}
				}
			}
			
			sb.append(cnt + "\n");
		}
		
		System.out.println(sb.toString());
		
		sc.close();
	}

	private static class Node{
		int x, y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static void DFS(int x, int y) {
		Stack<Node> st = new Stack<>();
		st.add(new Node(x, y));
		visited[y][x] = true;
		
		while(!st.isEmpty()) {
			Node node = st.pop();
			int[] dirY = {-1, -1, -1, 0, 0, 1, 1, 1};
			int[] dirX = {-1, 0, 1, -1, 1, -1, 0, 1};
			
			for (int i = 0; i < dirY.length; i++) {
				int nowX = node.x + dirX[i];
				int nowY = node.y + dirY[i];
				if(rangeCheck(nowX, nowY) && !visited[nowY][nowX] && map[nowY][nowX] == 1) {
					st.add(new Node(nowX, nowY));
					visited[nowY][nowX] = true;
				}
			}
			
		}
		
	}

	private static boolean rangeCheck(int nowX, int nowY) {
		return (nowX >= 0 && nowX < w && nowY >= 0 && nowY < h) ;
	}

}
