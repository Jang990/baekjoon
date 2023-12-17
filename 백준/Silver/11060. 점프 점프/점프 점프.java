import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int[] maze = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();
        br.close();

        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int step = maze[i];
            for (int j = 1; j <= step; j++) {
                int next = i+j;
                if(next >= n)
                    break;
                if(dp[i] >= dp[next])
                    continue;
                dp[next] = dp[i] + 1;
            }
        }

        if(dp[n-1] == Integer.MAX_VALUE)
            dp[n-1] = -1;

        System.out.println(dp[n-1]);
    }
}
