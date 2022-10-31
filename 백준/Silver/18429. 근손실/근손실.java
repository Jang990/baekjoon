import java.util.*;
import java.io.*;

public class Main {

	static int N,K;
	static int[] arr, outArr;
	static boolean[] visited;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		K = Integer.valueOf(st.nextToken());
		cnt = 0;
		visited = new boolean[N];
		outArr = new int[N];
		
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] -= K;
		}
		
		DFS(0, 500);
		
		System.out.println(cnt);
		
		br.close();
	}

	private static void DFS(int idx, int sum) {
		if(idx == N) {
			if(sum < 500) return;
			cnt++;
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(visited[i]) continue;
			if(sum + arr[i] < 500) continue;
			
			visited[i] = true;
			DFS(idx+1,sum + arr[i]);
			visited[i] = false;
		}
	}

}