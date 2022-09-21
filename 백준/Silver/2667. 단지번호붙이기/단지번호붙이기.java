import java.util.*;
public class Main {
	static int N;
	static int[][] apt;
	static boolean[][] check;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.nextLine();
		
		apt = new int[N][N];
		check = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = sc.nextLine();
			for (int j = 0; j < N; j++) {
				apt[i][j] = str.charAt(j) - '0';
			}
		}
		
		List<Integer> cntList = new ArrayList<>();
		int cnt = 0;
		for (int i = 0; i < apt.length; i++) {
			for (int j = 0; j < apt[i].length; j++) {
				if(check[i][j] == false && apt[i][j] == 1) {
					cnt++;
					int aptCnt = BFS(j, i);
					cntList.add(aptCnt);
				}
			}
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(cnt + "\n");
		cntList.stream().sorted().forEach(n -> sb.append(n+"\n"));
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
	
	private static int BFS(int x, int y) {
		int cnt = 1;
		Queue<Node> que = new LinkedList<>();
		check[y][x] = true;
		que.offer(new Node(x, y));
		int[] dirX = {1, -1, 0, 0};
		int[] dirY = {0, 0, 1, -1};
		
		while(!que.isEmpty()) {
			Node node = que.poll();
			
			for (int i = 0; i < 4; i++) {
				int nowX = node.x + dirX[i];
				int nowY = node.y + dirY[i];
				
				if(rangeCheck(nowX, nowY) && 
						!check[nowY][nowX] && 
						apt[nowY][nowX] == 1) {
					que.offer(new Node(nowX, nowY));
					check[nowY][nowX] = true;
					cnt++;
				}
			}
		}
		
		return cnt;
	}

	private static boolean rangeCheck(int nowX, int nowY) {
		return (nowX >= 0 && nowX < N && nowY >= 0 && nowY < N);
	}

}