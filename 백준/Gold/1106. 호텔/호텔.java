import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int cityCount = Integer.parseInt(line[1]);
        int maxClient = Integer.parseInt(line[0]);

        int[] dp = new int[maxClient+101];

        Arrays.fill(dp, 100*1000+1);
        dp[0] = 0;

        for (int i = 0; i < cityCount; i++) {
            line = br.readLine().split(" ");
            int adMoney = Integer.parseInt(line[0]);
            int adClient = Integer.parseInt(line[1]);

            for (int j = adClient; j < maxClient+101; j++) {
                dp[j] = Math.min(dp[j], adMoney + dp[j - adClient]);
            }
        }
        br.close();

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 101; i++) {
            answer = Math.min(answer, dp[maxClient+i]);
        }
        System.out.println(answer);
    }
}
