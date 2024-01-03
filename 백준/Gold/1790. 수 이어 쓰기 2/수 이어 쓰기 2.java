import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int n = Integer.parseInt(arr[0]);
        int k = Integer.parseInt(arr[1]);
        br.close();

        long base = 9;
        long digits = 1;
        while (true) {
            long limit = base * digits;
            if (k <= limit)
                break;

            k -= limit;
            base *= 10;
            digits++;
        }

        long result = k / digits;
        int mod = (int) (k % digits);
        result += Math.pow(10, digits-1);
        if (mod == 0) {
            result--;
        }
        if (result > n) {
            System.out.println(-1);
            return;
        }
        System.out.println(getAnswer(result, mod));

    }

    private static String getAnswer(long result, int mod) {
        if(mod == 0)
            return (result % 10) + "";

        mod--;
        return Long.toString(result).charAt(mod) + "";
    }
}
