import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken()); // 행
        int c = Integer.parseInt(st.nextToken()); // 열

        calc(N, r,c);
        System.out.println(result);
    }

    private static void calc(int n, int r, int c) {
        if(n <= 0) {
            return;
        }
        int range = (int)Math.pow(2, n-1);
        int nextR = r;
        int nextC = c;

        if(range <= r) {
            nextR = r-range;
            result += (long)range*range*2;
        }
        if (range <= c) {
            nextC = c-range;
            result += (long)range*range;
        }

        calc(n-1, nextR, nextC);
    }
}
