import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        br.close();

        int[][] dp = new int[s2.length()][s1.length()];
        dp[0][0] = (s1.charAt(0) == s2.charAt(0)) ? 1 : 0;
        for (int i = 1; i < dp.length; i++) {
            if(s1.charAt(0) == s2.charAt(i) || dp[i-1][0] == 1)
                dp[i][0] = 1;
        }
        for (int i = 1; i < dp[0].length; i++) {
            if(s1.charAt(i) == s2.charAt(0) || dp[0][i-1] == 1)
                dp[0][i] = 1;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if(s1.charAt(j) == s2.charAt(i))
                    dp[i][j] = dp[i-1][j-1]+1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        System.out.println(dp[s2.length()-1][s1.length()-1]);
    }
}
