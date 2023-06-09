import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);
        int[][] objects = new int[N+1][2];

        for (int i = 1; i <= N; i++) {
            line = br.readLine().split(" ");
            objects[i][0] = Integer.parseInt(line[0]);
            objects[i][1] = Integer.parseInt(line[1]);
        }

        br.close();

        int[][] dp = new int[N+1][K+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][j] = dp[i-1][j];
                int objWeight =objects[i][0];
                int objValue =objects[i][1];
                if (j >= objWeight) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-objWeight] + objValue);
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
