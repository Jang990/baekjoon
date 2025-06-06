import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();

        int max = Arrays.stream(arr).max().getAsInt();
        double sum = 0;
        for (int score : arr) {
            sum += 1.0 * score / max * 100;
        }

        System.out.println(sum / n);
    }
}
