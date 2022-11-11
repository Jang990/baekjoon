import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M;
	static int[][] graph;
	static boolean[] visited;
	static int Max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		
		graph = new int[M][2];
		visited = new boolean[N+1];
		int n1, n2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.valueOf(st.nextToken());
			n2 = Integer.valueOf(st.nextToken());
			
			graph[i][0] = n1;
			graph[i][1] = n2;
		}
		
		Rec(0,0);
		
		Max *= 2;
		
		if(Max < N) {
			Max++;
		}
		
		System.out.println(Max);
		
		br.close();
	}

	private static void Rec(int idx, int cnt) {
		if(idx == M) {
			Max = Math.max(Max, cnt);
			return;
		}
		
		int n1 = graph[idx][0], n2 = graph[idx][1];
		
		if(visited[n1] || visited[n2]) {
			Rec(idx +1, cnt);
		} else {
			visited[n1] = true;
			visited[n2] = true;
			
			Rec(idx+1, cnt+1);
			
			visited[n1] = false;
			visited[n2] = false;
			
			Rec(idx+1, cnt);
		}
	}
}