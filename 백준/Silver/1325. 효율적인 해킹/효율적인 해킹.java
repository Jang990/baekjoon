import java.io.*;
import java.util.*;

public class Main {

	static int N,M, max;
	static List<List<Integer>> graph;
	static boolean[] visited;
	static int[] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		max = 0;
		
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		visited = new boolean[N+1];
		result = new int[N+1];
		
		int n1, n2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.valueOf(st.nextToken());
			n2 = Integer.valueOf(st.nextToken());
			
			graph.get(n2).add(n1);
		}
		
		for (int i = 1; i <= N; i++) {
			BFS(i);
		}
		
		br.close();
		
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < result.length; i++) {
			if(max == result[i]) sb.append(i + " ");
		}
		System.out.println(sb);
	}

	private static void BFS(int i) {
		Queue<Integer> qu = new LinkedList<>();
		qu.offer(i);
		visited = new boolean[N+1];
		visited[i] = true;
		
		int cnt = 0;
		cnt++;
		
		int loc;
		while(!qu.isEmpty()) {
			loc = qu.poll();
			for (int num : graph.get(loc)) {
				if(!visited[num]) {
					qu.offer(num);
					visited[num] = true; 
					cnt++;
				}
			}
		}
		max = Math.max(cnt, max);
		result[i] = cnt;
	}

}