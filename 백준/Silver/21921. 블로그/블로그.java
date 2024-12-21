import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line.split(" ")[0]);
        int X = Integer.parseInt(line.split(" ")[1]);

        int[] visitors = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();

        Queue<Integer> qu = initQueue(X, visitors);
        int sum = qu.stream().mapToInt(Integer::valueOf).sum();

        int max = sum;
        int maxDays = 1;

        for (int day = X; day < N; day++) {
            sum -= qu.poll();
            sum += visitors[day];
            qu.offer(visitors[day]);

            if(sum < max)
                continue;
            if (sum == max) {
                maxDays++;
                continue;
            }
            maxDays = 1;
            max = sum;
        }

        if (max == 0) {
            System.out.println("SAD");
            return;
        }
        System.out.println(max);
        System.out.println(maxDays);
    }

    private static Queue<Integer> initQueue(int X, int[] visitors) {
        Queue<Integer> qu = new LinkedList<>();
        for (int i = 0; i < X; i++) {
            qu.offer(visitors[i]);
        }
        return qu;
    }
}
