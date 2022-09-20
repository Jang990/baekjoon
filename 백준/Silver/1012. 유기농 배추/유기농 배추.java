import java.util.*;
public class Main {
	
	static int M;
	static int N;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) {
		int testCase;
		Scanner sc = new Scanner(System.in);
		testCase = sc.nextInt();
		sc.nextLine();
		StringBuffer sb = new StringBuffer();
		
		while(testCase != 0) {
			M = sc.nextInt();
			N = sc.nextInt();
			int K = sc.nextInt();
			map = new int[N][M];
			visited = new boolean[N][M];
			for (int i = 0; i < K; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				sc.nextLine();
				map[y][x] = 1;
			}
			
			int cnt = 0;
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					if(visited[j][k] == false && map[j][k] == 1) {
						cnt++;
						BFS(k, j);
					}
				}
			}
			sb.append(cnt + "\n");
			
			testCase--;
		}
		sc.close();
		
		System.out.println(sb.toString());
 	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static void BFS(int x, int y) {
		int dirX[] = {0, 0, -1, 1};
		int dirY[] = {-1, 1, 0, 0};
		
		Queue<Node> que = new LinkedList<>();
		visited[y][x] = true;
		que.offer(new Node(x, y));
		
		while(!que.isEmpty()) {
			Node node = que.poll();
			
			for (int i = 0; i < 4; i++) {
				int now_x = node.x + dirX[i];
				int now_y = node.y + dirY[i];
				
				if(rangeCheck(now_y,now_x) && visited[now_y][now_x] == false && map[now_y][now_x] == 1) {
					que.offer(new Node(now_x, now_y));
					visited[now_y][now_x] = true;
				}
			}
		}
	}

	private static boolean rangeCheck(int now_y, int now_x) {
		return (now_x >= 0 && now_x < M && now_y >= 0 && now_y < N);
	}

}
