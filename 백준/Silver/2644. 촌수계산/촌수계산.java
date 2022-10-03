import java.util.*;

public class Main {
	
	static int[][] graph;
	static boolean[] visited;
	static int N;
	static int x, y;
	static int[] cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
//		sc.nextLine();
		
		x = sc.nextInt();
		y = sc.nextInt();
//		sc.nextLine();
		
		graph = new int[N+1][N+1];
		visited = new boolean[N+1];
		cnt = new int[N+1];
		
		int m = sc.nextInt();
		sc.nextLine();
		
		int a,b;
		for (int i = 0; i < m; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			
			graph[a][b] = 1;
			graph[b][a] = 1;
			
//			sc.nextLine();
		}
		
		BFS(x);
		int result;
		if(cnt[y] == 0) result = -1;
		else result = cnt[y];
		
		System.out.println(result);
		
		sc.close();
	}

	private static void BFS(int start) {
		Queue<Integer> qu = new LinkedList<>();
		
		qu.offer(start);
		visited[start] = true;
		
		while(!qu.isEmpty()) {
			int num = qu.poll();
			int count = cnt[num];
			
			for (int i = 0; i < graph[num].length; i++) {
				if(!visited[i] && graph[num][i] == 1) {
					qu.offer(i);
					visited[i] = true;
					cnt[i] = count+1;
					if(i == y) return;
				}
			}
		}
	}

}