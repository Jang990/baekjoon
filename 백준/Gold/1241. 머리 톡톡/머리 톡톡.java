import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] cnt;
    static int[] input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        cnt = new int[1_000_001];
        input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(br.readLine());
            cnt[input[i]]++;
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        for (int num : input) {
            sb.append(toktok(num)).append("\n");
        }
        System.out.println(sb);
    }

    private static int toktok(int num) {
        if(num == 1)
            return cnt[1] - 1;

        int result = cnt[1] + cnt[num] - 1;
        int sqrt = (int) Math.sqrt(num);
        for (int i = 2; i <= sqrt; i++) {
            if(num % i != 0)
                continue;
            if(i == (num/i))
                result += cnt[i];
            else
                result += cnt[i] + cnt[num / i];
        }
        return result;
    }
}
