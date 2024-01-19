import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int b = Integer.parseInt(tokenizer.nextToken());
        br.close();

        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.insert(0, convert(n % b));
            n /= b;
        }
        System.out.println(sb);
    }

    private static String convert(int n) {
        if(n < 10)
            return String.valueOf(n);
        n -= 10;
        char c = (char) ('A' + n);
        return String.valueOf(c);
    }
}
