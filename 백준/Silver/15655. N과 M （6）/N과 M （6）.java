import java.util.*;

public class Main {
	static int N, M;
	static int[] num, arr;
	static boolean[] visited;
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		num = new int[N];
		arr = new int[M];
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			num[i] = sc.nextInt();
		}
		num = Arrays.stream(num).sorted().toArray();
		sc.close();
		Rec(0);
		System.out.println(sb);
	}
	
	private static void Rec(int idx) {
		if(idx == M) {
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < num.length; i++) {
			if(!visited[i] && checkArr(idx, num[i])) {
				visited[i] = true;
				arr[idx] = num[i];
				Rec(idx+1);
				visited[i] = false;
			}
		}
	}

	private static boolean checkArr(int idx, int num2) {
		for (int i = 0; i < idx; i++) {
			if(arr[i] > num2) {
				return false;
			}
		}
		return true;
	}

}