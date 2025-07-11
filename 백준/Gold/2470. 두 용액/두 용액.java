import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] inputs = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        br.close();

        int leftIdx = 0;
        int rightIdx = N - 1;

        int resultL = inputs[leftIdx];
        int resultR = inputs[rightIdx];
        int result = Math.abs(resultR + resultL);
        while (leftIdx < rightIdx && result != 0) {
            int current = inputs[rightIdx] + inputs[leftIdx];
            if (Math.abs(current) < result) {
                resultL = inputs[leftIdx];
                resultR = inputs[rightIdx];
                result = Math.abs(current);
            }

            if (current < 0) {
                leftIdx++;
            } else {
                rightIdx--;
            }
        }

        System.out.println(resultL + " " + resultR);
    }
}
