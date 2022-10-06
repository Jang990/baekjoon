import java.util.*;

public class Main {

	static int N;
	static int[] arr;
	static int[] calcArr;
	static boolean[] visited;
	static int sum;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N];
		calcArr = new int[N];
		visited = new boolean[N];
		sum = 0;
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		
		Rec(0);
		
		System.out.println(sum);
		
		sc.close();
	}

	private static void Rec(int idx) {
		if(idx == N) {
			int num = calc();
			if(sum < num)
				sum = num;
		}
		
		for (int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				calcArr[idx] = arr[i];
				Rec(idx+1);
				visited[i] = false;
			}
		}
	}

	private static int calc() {
		int check = 0;
		for (int i = 0; i < calcArr.length - 1; i++) {
			check += Math.abs(calcArr[i] - calcArr[i+1]);
		}
		
		return check;
	}

}