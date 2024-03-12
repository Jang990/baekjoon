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

        int[] result = new int[n];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < result.length; j++) {
                if (result[j] == 0 || arr[i] <= result[j]) {
                    result[j] = arr[i];
                    break;
                }
            }
        }

        System.out.println(Arrays.stream(result).filter(i -> i != 0).count());
    }
}
