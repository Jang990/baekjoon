import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        br.close();

        long N = Long.parseLong(input[0]);
        long L = Long.parseLong(input[1]);

        StringBuilder sb = new StringBuilder();
        for (long count = L; count <= 100; count++) {
            long countSum = N - sumZeroToNum(count - 1);
            if (countSum < 0) {
                sb.append(-1);
                break;
            }

            if (countSum % count != 0) {
                continue;
            }

            long start = countSum/count;
            for (long j = 0; j < count; j++) {
                sb.append((start + j) + " ");
            }
            break;
        }

        if (sb.toString().length() < 1) {
            sb.append(-1);
        }

        System.out.println(sb.toString());
    }

    public static long sumZeroToNum(long num) {
        return num*(num+1)/2;
    }
}
