import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		List<Integer> arr = new ArrayList<>();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					arr.add(BFS(j,i));
				}
			}
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(arr.size() + "\n");
		Optional<Integer> i = arr.stream().sorted(Comparator.reverseOrder()).findFirst();
		if(i.isEmpty()) 
			sb.append("0");
		else
			sb.append(i.get());
		
		System.out.println(sb.toString());
		
		sc.close();
	}
	
	static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	private static int BFS(int startX, int startY) {
		int cnt = 1;
		Queue<Node> qu = new LinkedList<>();
		
		qu.offer(new Node(startX, startY));
		visited[startY][startX] = true;
		
		int[] dirX = {0,0,1,-1};
		int[] dirY = {1,-1,0,0};
		int nowX, nowY;
		while(!qu.isEmpty()) {
			Node node = qu.poll();
			for (int i = 0; i < dirY.length; i++) {
				nowX = node.x + dirX[i]; 
				nowY = node.y + dirY[i];
				
				if(rangeCheck(nowX, nowY) && !visited[nowY][nowX] && map[nowY][nowX] == 1) {
					qu.offer(new Node(nowX, nowY));
					cnt++;
					visited[nowY][nowX] = true;
				}
			}
		}
		
		return cnt;
	}

	private static boolean rangeCheck(int nowX, int nowY) {
		return (nowX >= 0 && nowX < map[0].length && nowY >= 0 && nowY < map.length);
	}
}