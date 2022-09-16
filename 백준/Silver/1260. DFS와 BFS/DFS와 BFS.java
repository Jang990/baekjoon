import java.util.*;

public class Main {
	static int N, M, V;
	static int[][] graph;
	static int[] check;
	static StringBuffer sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();
		sc.nextLine();
		
		graph = new int[N+1][N+1];
		check = new int[N+1];
		
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			sc.nextLine();
			
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		
		sb = new StringBuffer();
		dfs(V);
		sb.append("\n");
		check = new int[N+1];
		bfs(V);
		
		System.out.println(sb.toString());
		sc.close();
	}
	
	private static void dfs(int start) {
		check[start] = 1;
		sb.append(start + " ");
		for(int j=1; j< graph[start].length; j++) {
			if(graph[start][j] == 1 && check[j] != 1) {
				dfs(j);
			}		
		}
	}
	
	private static void bfs(int start) {
		Queue<Integer> qu = new LinkedList<Integer>();
		
		qu.offer(start);
		check[start] = 1;
		while(!qu.isEmpty()) {
			int x = qu.poll();
			sb.append(x+" ");
			for (int i = 1; i < graph[x].length; i++) {
				if(graph[x][i] == 1 && check[i] != 1) {
					qu.offer(i);
					check[i] = 1;
				}
			}
		}
		
		sb.append("\n");
	}
	
}