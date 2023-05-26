import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cups = new int[n];
        for (int i = 0; i < n; i++) {
            cups[i] = Integer.parseInt(br.readLine());
        }

        br.close();

        int[][] dp = new int[n][3];
        dp[0][0] = 0;
        dp[0][1] = cups[0];
        dp[0][2] = cups[0];

        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = getMax(dp[i-1]);
            dp[i][1] = dp[i-1][0] + cups[i];
            dp[i][2] = dp[i-1][1] + cups[i];
        }

        System.out.println(getMax(dp[n-1]));
    }

    static int getMax(int[] arr) {
        return Math.max(Math.max(arr[0], arr[1]), arr[2]);
    }
}
