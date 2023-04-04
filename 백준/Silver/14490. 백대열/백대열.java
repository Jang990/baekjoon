import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] numbers = s.split(":");

        int n1 = Integer.parseInt(numbers[0]);
        int n2 = Integer.parseInt(numbers[1]);

        int result = 0;
        if(n2 > n1) {
            result = gcd(n2, n1);
        }
        else {
            result = gcd(n1, n2);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(n1/result);
        sb.append(":");
        sb.append(n2/result);

        System.out.println(sb.toString());

        br.close();
    }

    static int gcd(int a, int b) {
        while(b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }
}
