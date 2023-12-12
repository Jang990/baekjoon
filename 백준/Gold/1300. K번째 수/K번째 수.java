import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static long n;
    private static long k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.valueOf(br.readLine());
        k = Long.valueOf(br.readLine());
        br.close();

        long left = 1, right = k;
        long mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if(getCnt(mid) >= k)
                right = mid;
            else
                left = mid+1;
        }
        System.out.println(left);
    }

    private static long getCnt(long mid) {
        long cnt = 0;
        for (int i = 1; i <= n; i++) {
            long result = mid / i;
            cnt += Math.min(result, n);
            if(result == 0)
                break;
        }
        return cnt;
    }
}
