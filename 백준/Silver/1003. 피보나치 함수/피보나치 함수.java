import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int zeroCnt;
    static int oneCnt;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        dp = new int[41][2];
        dp[0][0] = 1; dp[0][1] = 0;
        dp[1][0] = 0; dp[1][1] = 1;

        for (int i = 0; i < T; i++) {
            zeroCnt = 0;
            oneCnt = 0;

            int N = Integer.parseInt(br.readLine());
            for (int j = 2; j <= N; j++) {
                if(dp[j][0] != 0 ||  dp[j][1] != 0)
                    continue;

                dp[j][0] = dp[j-1][0] + dp[j-2][0];
                dp[j][1] = dp[j-1][1] + dp[j-2][1];
            }

            sb.append(dp[N][0] + " " + dp[N][1] + "\n");
        }
        br.close();

        System.out.println(sb.toString());
    }
}
