import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int[][] job = new int[n][2];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            String[] arr = br.readLine().split(" ");
            job[i][0] = Integer.valueOf(arr[0]);
            job[i][1] = Integer.valueOf(arr[1]);
        }
        br.close();

        if(job[0][0] - 1 < n)
            dp[job[0][0]-1] = job[0][1];
        
        for (int i = 1; i < n; i++) {
            int time = job[i][0];
            int pay = job[i][1];
            int next = i + time - 1;
            dp[i] = Math.max(dp[i], dp[i-1]);
            if(next >= n)
                continue;
            dp[next] = Math.max(dp[next], dp[i-1] + pay);
        }
        
        System.out.println(dp[n-1]);
    }
}
