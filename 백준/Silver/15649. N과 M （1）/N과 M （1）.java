import java.util.*;

public class Main {
	static int N, M;
	static int[] arr;
	static boolean[] visited;
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[M + 1];
		visited = new boolean[N + 1];
		
		Rec(0);
		System.out.println(sb.toString());
		
		sc.close();
	}

	private static void Rec(int idx) {
		if(idx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[idx] = i;
				Rec(idx+1);
				visited[i] = false;
			}
		}
	}

}