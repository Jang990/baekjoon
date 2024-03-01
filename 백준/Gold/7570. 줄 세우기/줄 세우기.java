import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();

        int[] dp = new int[n + 1];
        int max = 0;
        for (int i = 0; i < n; i++) {
            int idx = arr[i];
            dp[idx] = dp[idx - 1] + 1;
            max = Math.max(dp[idx], max);
        }

        System.out.println(n - max);
    }
}
