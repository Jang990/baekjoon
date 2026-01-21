import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = readLine(br);

        int M = line[1];
        int[] arr = readLine(br);
        br.close();

        int result = 0;
        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : arr) {
            sum += num;
            pq.offer(num);

            while (sum >= M) {
                Integer m = pq.poll();
                sum -= (m * 2);
                result++;
            }
        }

        System.out.println(result);
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
