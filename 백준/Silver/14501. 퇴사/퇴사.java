import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int[] dp = new int[n+1];
        int max = 0;
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.valueOf(st.nextToken());
            int p = Integer.valueOf(st.nextToken());
            int completionDate = i + t-1;
            dp[i] = Math.max(dp[i - 1], dp[i]);
            if (completionDate > n) {
                continue;
            }
            dp[completionDate] = Math.max(dp[i-1] + p, dp[completionDate]);
            max = Math.max(dp[completionDate], max);
        }
        br.close();

        System.out.println(max);
    }
}
