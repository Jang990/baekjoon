import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][3];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        int[][][] dp = new int[2][n][3];
        for (int i = 0; i < 3; i++) {
            dp[0][0][i] = map[0][i];
            dp[1][0][i] = map[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
                for (int k = 0; k < 3; k++) {
                    if(j == 0 && k == 2)
                        continue;
                    if(j == 2 && k == 0)
                        continue;

                    min = Math.min(min, map[i][j] + dp[0][i - 1][k]);
                    max = Math.max(max, map[i][j] + dp[1][i - 1][k]);
                }
                dp[0][i][j] = min;
                dp[1][i][j] = max;
            }
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            min = Math.min(min, dp[0][n - 1][i]);
            max = Math.max(max, dp[1][n - 1][i]);
        }

        System.out.println(max + " " + min);
    }
}
