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
        Arrays.sort(arr);


        int minusIdx = 0;
        int plusIdx = arr.length-1;
        int[] result = {1_000_000_000, 1_000_000_000};

        while (minusIdx < plusIdx) {
            int sum = arr[minusIdx] + arr[plusIdx];
            if (Math.abs(sum) < Math.abs(result[0] + result[1])) {
                result[0] = arr[minusIdx];
                result[1] = arr[plusIdx];
            }

            if (sum == 0)
                break;
            else if (sum < 0)
                minusIdx++;
            else
                plusIdx--;
        }

        System.out.println(result[0] + " " + result[1]);
    }
}
