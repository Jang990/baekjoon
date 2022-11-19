import java.util.*;
import java.io.*;

public class Main {

	static int N, S, cnt;
	static int[] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		S = Integer.valueOf(st.nextToken());
		cnt = 0;
		visited = new boolean[N];
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		Rec(0, 0, 0);
		
		System.out.println(cnt);
		
		br.close();
	}

	private static void Rec(int idx, int sum, int ind) {
		if(idx != 0 && sum == S) {
			cnt++;
		}
		
		if(idx == N) {
			return;
		}
		
		for (int i = ind; i < arr.length; i++) {
			if(visited[i])
				continue;
			
			visited[i] = true;
			Rec(idx+1, sum + arr[i], i);
			visited[i] = false;
		}
	}

}