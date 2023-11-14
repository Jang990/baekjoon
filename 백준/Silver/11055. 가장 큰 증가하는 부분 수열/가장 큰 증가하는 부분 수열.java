import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.valueOf(st.nextToken());
        }
        br.close();

        int[] dp = Arrays.copyOf(arr, n);
        for (int i = 1; i < arr.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if(arr[i] > arr[j])
                    max = Math.max(dp[j], max);
            }
            dp[i] += max;
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(dp[i], result);
        }

        System.out.println(result);
    }
}
