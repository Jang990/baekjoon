import java.util.*;

public class Main {
	static int N, M;
	static int[][] graph;
	static boolean[] visited;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sc.nextLine();
		
		graph = new int[N+1][N+1];
		visited = new boolean[N+1];
		
		int u, v;
		for (int i = 0; i < M; i++) {
			u = sc.nextInt();
			v = sc.nextInt();
			graph[u][v] = 1;
			graph[v][u] = 1;
			sc.nextLine();
		}
		
		int cnt = 0;
		for (int i = 1; i < visited.length; i++) {
			if(!visited[i]) {
				cnt++;
				BFS(i);
			}
		}
		
		System.out.println(cnt);
		
		sc.close();
	}


	private static void BFS(int start) {
		Queue<Integer> que = new LinkedList<>();
		que.offer(start);
		visited[start] = true;
		
		while(!que.isEmpty()) {
			int x= que.poll();
			for (int i = 1; i < graph.length; i++) {
				if(graph[x][i] == 1 && !visited[i]) {
					que.offer(i);
					visited[i] = true;
				}
			}
		}
	}

}