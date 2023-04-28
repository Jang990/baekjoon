import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int[] dp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();

        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if (dp[i - 1] <= 0) {
                max = Math.max(max, dp[i]);
                continue;
            }

            int result = dp[i - 1] + dp[i];
            if (result > 0) {
                dp[i] = result;
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
