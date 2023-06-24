import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).sorted().toArray();
        br.close();

        int result = 1;
        while (true) {
            int sum = 0;
            for (int i = N-1; i >= 0; i--) {
                if (sum + arr[i] <= result) {
                    sum += arr[i];
                }
            }

            if (sum != result) {
                break;
            }

            result++;
        }

        System.out.println(result);
    }
}
