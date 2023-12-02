import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] cond = br.readLine().split(" ");
        int n = Integer.parseInt(cond[0]);
        int money = Integer.parseInt(cond[1]);
        int[] coin = new int[n];
        for (int i = 0; i < n; i++)
            coin[i] = Integer.parseInt(br.readLine());
        br.close();

        int min = Integer.MAX_VALUE;
        for (int i = n-1; i >= 0; i--) {
            int now = money;
            int coinCnt = 0;
            for (int j = i; j >= 0; j--) {
                if(now < coin[j])
                    continue;

                coinCnt += (now / coin[j]);
                now %= coin[j];
                if(now == 0)
                    break;
            }

            if(now == 0)
                min = Math.min(min, coinCnt);
        }

        System.out.println(min);
    }
}
