import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] roads = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        long[] fuels = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        br.close();

        long result = 0;
        long currentFuel = fuels[0];
        int startPoint = 0;
        for (int i = 1; i < N; i++) {
            if(currentFuel < fuels[i])
                continue;

            long money = sum(roads, startPoint, i - 1) * currentFuel;
            startPoint = i;
            currentFuel = fuels[i];
            result += money;
        }

        if (startPoint <= N - 2) {
            long money = sum(roads, startPoint, N - 2) * currentFuel;
            result += money;
        }

        System.out.println(result);
    }

    private static long sum(long[] roads, int start, int end) {
        long result = 0;
        for (int i = start; i <= end; i++)
            result += roads[i];
        return result;
    }
}
