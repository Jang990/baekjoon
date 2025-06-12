import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();

        int min = Integer.MAX_VALUE, sum = 0;
        int start = 0, end = 0;
        while (start < n || end < n) {
            if (sum < m) {
                if(end >= n)
                    break;
                sum += arr[end];
                end++;
            } else {
                min = Math.min(min, end - start);
                sum -= arr[start];
                start++;
            }
        }

        if(min == Integer.MAX_VALUE)
            min = 0;

        System.out.println(min);
    }
}
