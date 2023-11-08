import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int coinCnt = Integer.valueOf(st.nextToken());
        int money = Integer.valueOf(st.nextToken());
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < coinCnt; i++) {
             set.add(Integer.valueOf(br.readLine()));
        }
        br.close();

        int[] coins = set.stream().mapToInt(Integer::valueOf).toArray();
        int[] dp = new int[money+1];
        Arrays.fill(dp, Integer.MAX_VALUE-1);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            int now = coins[i];
            for (int j = now; j <= money; j++) {
                dp[j] = Math.min(dp[j], dp[j-now]+1);
            }
        }

        if (dp[money] == Integer.MAX_VALUE - 1)
            System.out.println(-1);
        else
            System.out.println(dp[money]);
    }
}
