import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int minSum, maxSum, diffMin;
    static int result = 0;
    private static int[] problems;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] cond = readInts(br);
        minSum = cond[1];
        maxSum = cond[2];
        diffMin = cond[3];

        problems = readInts(br);
        br.close();

        rec(0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        System.out.println(result);
    }

    private static void rec(int idx, int problemCnt, int min, int max, int sum) {
        if (idx >= problems.length) {
            if(problemCnt >= 2 && max - min >= diffMin && minSum <= sum && sum <= maxSum)
                result++;
            return;
        }

        rec(idx + 1, problemCnt, min, max, sum);
        rec(
                idx + 1,
                problemCnt + 1,
                Math.min(min, problems[idx]),
                Math.max(max, problems[idx]),
                sum + problems[idx]
        );
    }

    private static int[] readInts(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
