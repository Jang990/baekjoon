import java.util.*;

public class Main {

	static int[] arr;
	static int[] visited;
	static int N, M;
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[M];
		visited = new int[N+1];
		
		Rec(0);
		System.out.println(sb);
		sc.close();
	}

	private static void Rec(int idx) {
		if(idx == M) {
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if(visited[i] < N) {
				visited[i]++;
				arr[idx] = i;
				Rec(idx+1);
				visited[i]--;
			}
		}
	}

}
