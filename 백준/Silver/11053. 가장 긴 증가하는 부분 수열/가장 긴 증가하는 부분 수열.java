import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();
        br.close();

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(arr[i] > arr[j])
                    dp[i] = Math.max(dp[j]+1, dp[i]);
            }
        }

        int result = 1;
        for (int i = 0; i < n; i++) {
            result = Math.max(dp[i], result);
        }
        System.out.println(result);
    }
}
