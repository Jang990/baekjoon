import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseCnt = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < caseCnt; i++) {
            int day = Integer.parseInt(br.readLine());
            long result = 0;
            long[] stock = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            long max = stock[day-1];
            for (int j = day-1; j >= 0; j--) {
                if (max < stock[j]) {
                    max = stock[j];
                }

                long profit = max - stock[j];
                result += profit;
            }

            sb.append(result + "\n");
        }
        br.close();

        System.out.println(sb);
    }
}
