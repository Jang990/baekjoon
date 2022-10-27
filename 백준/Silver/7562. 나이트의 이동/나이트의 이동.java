import java.util.*; 
import java.io.*;

public class Main {
	static int N;
	static int[][] board;
	static boolean[][] visited;
	static StringBuffer sb = new StringBuffer();
	static Node start, end;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.valueOf(br.readLine());
		
		for (int i = 0; i < test; i++) {
			N = Integer.valueOf(br.readLine());
			board = new int[N][N];
			visited = new boolean[N][N];
			String[] str = br.readLine().split(" ");
			start = new Node(Integer.valueOf(str[0]), Integer.valueOf(str[1]));
			str = br.readLine().split(" ");
			end = new Node(Integer.valueOf(str[0]), Integer.valueOf(str[1]));
			
			if(start.x == end.x && start.y == end.y) {
				sb.append("0\n");
				continue;
			}
			
			BFS(start); 
			sb.append(board[end.y][end.x] + "\n");
		}
		System.out.println(sb);
		
		br.close();
	}
	
	private static void BFS(Node startNode) {
		Queue<Node> qu = new LinkedList<>();
		qu.offer(startNode);
		visited[startNode.y][startNode.x] = true;
		
		int[] dirX = {1, -1, 2, 2, 1, -1, -2, -2};
		int[] dirY = {2, 2, 1, -1, -2, -2, -1, 1};
		int nowX, nowY;
		Node node;
		while(!qu.isEmpty()) {
			node = qu.poll();
			for (int i = 0; i < dirY.length; i++) {
				nowX = node.x + dirX[i];
				nowY = node.y + dirY[i];
				if(rangeCheck(nowX, nowY) && !visited[nowY][nowX]) {
					board[nowY][nowX] = board[node.y][node.x] + 1;
					if(nowX == end.x && nowY == end.y)
						return;
					visited[nowY][nowX] = true;
					qu.offer(new Node(nowX, nowY));
				}
			}
		}
	}

	private static boolean rangeCheck(int nowX, int nowY) {
		return (N > nowX && nowX >= 0 && N > nowY && nowY >= 0);
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}