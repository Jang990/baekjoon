import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = init(br, n);
        br.close();

        int[][] dp = new int[n][n];
        dp[0][0] = arr[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = arr[i][j] + dp[i - 1][j];
                if(j == 0) continue;
                dp[i][j] = Math.max(dp[i][j], arr[i][j] + dp[i - 1][j - 1]);
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dp[n - 1][i]);
        }
        System.out.println(result);
    }

    private static int[][] init(BufferedReader br, int n) throws IOException {
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] in = br.readLine().split(" ");
            for (int j = 0; j < in.length; j++) {
                arr[i][j] = Integer.parseInt(in[j]);
            }
        }
        return arr;
    }
}
