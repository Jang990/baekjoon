import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] graph;
	static boolean[] visited;
	static int Min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.valueOf(br.readLine());
		graph = new int[N][N];
		visited = new boolean[N];
		Min = Integer.MAX_VALUE;
		
		for (int i = 0; i < graph.length; i++) {
			graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		br.close();
		
		visited[0] = true;
		BFS(0,0,1,0);
		System.out.println(Min);
		
	}

	private static void BFS(int start, int now, int cnt, int cost) {
		if(cnt == N) {
			if(graph[now][start] != 0) {
				cost += graph[now][start];
				Min = Math.min(Min, cost);
			}
			
			return;
		}
		
		for (int i = 0; i < graph.length; i++) {
			if(graph[now][i] == 0) 
				continue;
			
			if(!visited[i]) {
				visited[i] = true;
				BFS(start, i, cnt+1, cost + graph[now][i]);
				visited[i] = false;
			}
		}
	}

}