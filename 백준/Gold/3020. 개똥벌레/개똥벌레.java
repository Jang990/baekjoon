import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int[] dp = new int[height+1];
        dp[0] = n / 2;
        for (int i = 0; i < n; i++) {
            int now = Integer.parseInt(br.readLine());
            if (i % 2 == 0)
                dp[now+1]--;
            else
                dp[height - now + 1]++;
        }
        br.close();

        for (int i = 1; i < dp.length; i++) {
            dp[i] += dp[i-1];
        }

        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 1; i <= height; i++) {
            if (dp[i] < min) {
                min = dp[i];
                cnt = 1;
            }
            else if (dp[i] == min)
                cnt++;

        }
        System.out.println(min + " " + cnt);
    }
}
