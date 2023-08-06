import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        if (N <= 2) {
            System.out.println(N);
            return;
        }

        int result = 0;
        int n1 = 1;
        int n2 = 2;
        for (int i = 3; i <= N; i++) {
            result = (n1 + n2) % 10_007;
            n1 = n2;
            n2 = result;
        }


        br.close();

        System.out.println(result%10_007);
    }

}
