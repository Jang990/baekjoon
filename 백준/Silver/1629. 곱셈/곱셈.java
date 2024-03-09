import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int base = Integer.parseInt(st.nextToken());
        int pow = Integer.parseInt(st.nextToken());
        int mod = Integer.parseInt(st.nextToken());
        br.close();

        long result = rec(base, pow, mod);
        System.out.println(result % mod);
    }

    private static long rec(long base, long pow, long mod) {
        if(pow == 1)
            return base;
        if(pow % 2 == 1)
            return rec(base, pow - 1, mod) * base % mod;
        long result = rec(base, pow / 2, mod);
        return result * result % mod;
    }
}
