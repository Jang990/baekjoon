import java.io.*;

public class Main {
	static int N;
	static int[] dp, score;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		score = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			dp[i] = Integer.parseInt(br.readLine()); 
		}
		
		score[1] = dp[1];
		if(N == 1) {
			System.out.println(score[N]); 
			return;
		};
		score[2] = dp[1] + dp[2];
		if(N == 2) {
			System.out.println(score[N]); 
			return;
		};
		score[3] = Math.max(dp[1], dp[2]) + dp[3];
		
		for (int i = 4; i <= N; i++) {
			score[i] = Math.max(score[i-3] + dp[i-1], score[i-2]) + dp[i];
		}
		System.out.println(score[N]);
		br.close();
	}

}