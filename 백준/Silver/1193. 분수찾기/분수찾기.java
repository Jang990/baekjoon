import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        br.close();

        int i = 1;
        int prev = 0;
        int sum = 0;
        while (sum <= 10_000_000) {
            sum += i;
            if (prev < n && n <= sum) {
                break;
            }
            prev = sum;
            i++;
        }

        int num1 = i, num2 = 1;

        int idx = n - prev;
        for (int j = 1; j < idx; j++) {
            num1--;
            num2++;
        }

        if (i % 2 == 0) {
            System.out.println(num2 + "/" + num1);
        } else {
            System.out.println(num1 + "/" + num2);
        }
    }
}
