import java.util.*;
import java.io.*;

public class Main {

	static int Max = Integer.MIN_VALUE;
	static int[][] map;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < map.length; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		br.close();
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 1) 
					map[i][j] = -1;
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j] == -1) 
					BFS(new Node(j, i));
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				Max = Math.max(Max, map[i][j]);
			}
		}
		
		System.out.println(Max);
	}

	private static void BFS(Node start) {
		Queue<Node> qu = new LinkedList<>();
		qu.offer(start);
		
		int[] dirX = {-1,0,1,-1,1,-1,0,1};
		int[] dirY = {-1,-1,-1,0,0,1,1,1};
		int nowX, nowY;
		Node node;
		while(!qu.isEmpty()) {
			node = qu.poll(); 
			for (int i = 0; i < dirY.length; i++) {
				nowX = node.x + dirX[i];
				nowY = node.y + dirY[i];
				
				if(!rangeCheck(nowX, nowY) || map[nowY][nowX] == -1) 
					continue;
				
				if(map[nowY][nowX] != 0 && map[node.y][node.x] + 1 >= map[nowY][nowX])
					continue;
				
				if(map[node.y][node.x] == -1)
					map[nowY][nowX] = 1;
				else
					map[nowY][nowX] = map[node.y][node.x] + 1;
				
				qu.offer(new Node(nowX, nowY));
			}
		}
		
	}
	
	private static boolean rangeCheck(int nowX, int nowY) {
		return (0 <= nowX && nowX < M && 0 <= nowY && nowY < N);
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}