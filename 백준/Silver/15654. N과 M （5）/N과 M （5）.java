import java.util.*;

public class Main {
	
	static int[] inputArr;
	static int[] arr;
	static boolean[] visited;
	static int N, M;
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int[M];
		visited = new boolean[N];
		inputArr = new int[N];
		
		for (int i = 0; i < N; i++) {
			inputArr[i] = sc.nextInt();
		}
		
		Arrays.sort(inputArr);
		
		Rec(0);
		System.out.println(sb.toString());
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
		
		for (int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[idx] = inputArr[i];
				Rec(idx+1);
				visited[i] = false;
			}
		}
	}

}
