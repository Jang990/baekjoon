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

        int[] dp = new int[n];
        int max = 1, maxIdx = 0;
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            if (max < dp[i]) {
                max = dp[i];
                maxIdx = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(arr[maxIdx]);
        int nowIdx = maxIdx;
        for (int i = maxIdx - 1; i >= 0; i--) {
            if (arr[nowIdx] > arr[i] && dp[nowIdx] - 1 == dp[i]) {
                sb.insert(0, " ");
                sb.insert(0, arr[i]);
                nowIdx = i;
            }
        }

        sb.insert(0, dp[maxIdx]+"\n");
        System.out.println(sb);
    }
}
