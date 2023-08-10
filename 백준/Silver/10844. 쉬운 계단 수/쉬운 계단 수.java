import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        br.close();

        dp = new long[N+1][10];
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        long mod = 1_000_000_000;
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) {
                    dp[i][0] = dp[i - 1][1];
                }
                else if (j == 9) {
                    dp[i][9] = dp[i - 1][8];
                }
                else {
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % mod;
                }
            }
        }

        long result = 0;
        for (int i = 0; i < 10; i++) {
            result += dp[N][i];
        }
        System.out.println(result % mod);
    }
}
