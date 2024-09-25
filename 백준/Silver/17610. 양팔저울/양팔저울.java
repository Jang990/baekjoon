import java.io.*;
import java.util.*;

public class Main {
    static boolean[] weights;
    static int[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();

        int maxWeight = Arrays.stream(input).sum();
        weights = new boolean[maxWeight + 1];

        rec(0, 0);

        int result = 0;
        for (int i = 1; i < weights.length; i++) {
            if(!weights[i])
                result++;
        }
        System.out.println(result);
    }

    private static void rec(int depth, int sum) {
        if(sum > 0)
            weights[sum] = true;
        if(depth == input.length)
            return;

        rec(depth + 1, sum + input[depth]);
        rec(depth + 1, sum - input[depth]);
        rec(depth + 1, sum);
    }
}
