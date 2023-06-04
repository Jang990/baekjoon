import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        br.close();

        if (n == 1) {
            System.out.println(0);
            return;
        }

        if (n == 2 || n == 3) {
            System.out.println(1);
            return;
        }

        int[] dp = new int[n + 1];
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;


        //dp[i] = Math.min(dp[i/2], dp[i/3] , dp[i-1]) + 1
        for (int i = 4; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            if (i % 2 == 0) {
                min = Math.min(min, dp[i / 2]);
            }
            if (i % 3 == 0) {
                min = Math.min(min, dp[i / 3]);
            }
            min = Math.min(min, dp[i-1]);

            dp[i] = min+1;
        }

        System.out.println(dp[n]);


    }
}
