import java.util.*;
import java.io.*;

public class Main {

	static int[] graph;
	static boolean[] visited;
	static int n, cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		graph = new int[n+1];
		visited = new boolean[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int idx = 1;
		while(st.hasMoreElements()) {
			graph[idx] = Integer.parseInt(st.nextToken());
			idx++;
		}
		
		int startIdx = Integer.parseInt(br.readLine());
		BFS(startIdx);
		System.out.println(cnt);
		
		br.close();
	}

	private static void BFS(int startIdx) {
		Queue<Integer> qu = new LinkedList<>();
		qu.offer(startIdx);
		visited[startIdx] = true;
		cnt++;
		
		int idx, jump;
		while(!qu.isEmpty()) {
			idx = qu.poll();
			jump = graph[idx];
			if(idx - jump > 0 && !visited[idx - jump]) {
				qu.offer(idx-jump);
				visited[idx-jump] = true;
				cnt++;
			}
			if(idx + jump <= n && !visited[idx+jump]) {
				qu.offer(idx+jump);
				visited[idx+jump] = true;
				cnt++;
			}
		}
		
	}

}