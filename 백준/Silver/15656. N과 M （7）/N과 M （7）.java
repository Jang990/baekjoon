import java.util.*;

public class Main {
	static int N,M;
	static int[] inputArr;
	static int[] outputArr;
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		inputArr = new int[N];
		outputArr = new int[M];
		
		for (int i = 0; i < N; i++) {
			inputArr[i] = sc.nextInt();
		}
		
		inputArr = Arrays.stream(inputArr).sorted().toArray();
		
		Rec(0);
		System.out.println(sb);
		sc.close();
	}

	private static void Rec(int idx) {
		if(idx == M) {
			for (int i = 0; i < outputArr.length; i++) {
				sb.append(outputArr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < inputArr.length; i++) {
			outputArr[idx] = inputArr[i];
			Rec(idx+1);
		}
	}
}
