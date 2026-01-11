import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        br.close();
        Arrays.sort(arr);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int score = arr[i] + n - i;
            max = Math.max(score, max);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            if(arr[i] + n >= max)
                result++;
        }
        System.out.println(result);
    }
}
