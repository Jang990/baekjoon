import java.util.*;

public class Main {

	static int M,N,K;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		K = sc.nextInt();
		
		map = new int[M][N];
		visited = new boolean[M][N];
		
		List<Integer> cnt = new ArrayList<>(); 
		
		int startX, startY, endX, endY;
		for (int i = 0; i < K; i++) {
			startX = sc.nextInt();
			startY = sc.nextInt();
			
			endX = sc.nextInt();
			endY = sc.nextInt();
			for (int j = startY; j < endY; j++) {
				for (int k = startX; k < endX; k++) {
					map[j][k] = 1;
				}
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 0 && !visited[i][j])
					cnt.add(DFS(i,j));
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append(cnt.size() + "\n");
		cnt.stream().sorted().forEach((n) -> sb.append(n + " "));
		System.out.println(sb.toString());
		
		sc.close();
	}
	
	static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int DFS(int startY, int startX) {
		Queue<Node> qu = new LinkedList<>();
		
		int[] dirX = {0,0,1,-1};
		int[] dirY = {1,-1,0,0};
		qu.offer(new Node(startX, startY));
		visited[startY][startX] = true;
		int cnt = 1;
		
		int nowX, nowY;
		while(!qu.isEmpty()) {
			Node n = qu.poll();
			for (int i = 0; i < dirY.length; i++) {
				nowX = n.x + dirX[i];
				nowY = n.y + dirY[i];
				if(rangeCheck(nowX, nowY) && !visited[nowY][nowX] && map[nowY][nowX] == 0) {
					qu.add(new Node(nowX, nowY));
					visited[nowY][nowX] = true;
					cnt++;
				}
			}
		}
		
		return cnt;
	}

	private static boolean rangeCheck(int nowX, int nowY) {
		return (nowX >= 0 && nowX < map[0].length && nowY >= 0 && nowY < map.length);
	}

}