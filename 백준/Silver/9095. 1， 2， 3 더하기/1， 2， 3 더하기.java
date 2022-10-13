import java.io.*;

public class Main {
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		N = Integer.parseInt(br.readLine());
		
		int num;
		int[] dp = new int[11];
		
		dp[1] = 1; 
		dp[2] = 2;  
		dp[3] = 4;
		//dp[4] = 7;  
		
		for (int i = 0; i < N; i++) {
			num = Integer.parseInt(br.readLine());
			for (int j = 4; j <= num; j++) {
				dp[j] = dp[j -1] + dp[j-2] + dp[j-3];
			}
			sb.append(dp[num] + "\n");
		}
		System.out.println(sb);
	}

}
