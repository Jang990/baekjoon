import java.io.*; 
import java.util.*;

public class Main {
	static StringBuffer sb = new StringBuffer();
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int sheepCnt, wolfCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M];
		sheepCnt = 0;
		wolfCnt = 0;
		
		String str;
		for (int i = 0; i < map.length; i++) {
			str = br.readLine();
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(!visited[i][j] && map[i][j] != '#') {
					BFS(j,i);
				}
					
			}
		}
		
		System.out.println(sheepCnt + " " + wolfCnt);
		
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
		List<Node> wolf = new ArrayList<>();
		List<Node> sheep = new ArrayList<>();
		qu.offer(new Node(x,y));
		visited[y][x] = true;
		
		Node node;
		int[] dirX = {0,0,1,-1};
		int[] dirY = {1,-1,0,0};
		int nowX, nowY;
		while(!qu.isEmpty()) {
			node = qu.poll();
			
			if(map[node.y][node.x] == 'o')
				sheep.add(new Node(node.x,node.y));
			else if(map[node.y][node.x] == 'v')
				wolf.add(new Node(node.x,node.y));
			
			for (int i = 0; i < dirY.length; i++) {
				nowX = node.x + dirX[i];
				nowY = node.y + dirY[i];
				
				if(rangeCheck(nowX, nowY) && !visited[nowY][nowX] && map[nowY][nowX] != '#') {
					qu.offer(new Node(nowX, nowY));
					visited[nowY][nowX] = true;
				}
				
			}
		}
		
		if(sheep.size() == 0 && wolf.size() == 0) 
			return;
		
		if(sheep.size() > wolf.size()) {
			for (int i = 0; i < wolf.size(); i++) {
				node = wolf.get(i);
				map[node.y][node.x] = '.';
			}
			sheepCnt += sheep.size();
		}
		else {
			for (int i = 0; i < sheep.size(); i++) {
				node = sheep.get(i);
				map[node.y][node.x] = '.';
			}
			wolfCnt += wolf.size();
		}
	}

	private static boolean rangeCheck(int nowX, int nowY) {
		return (nowX >= 0 && nowX < M && nowY >= 0 && nowY < N);
	}

}