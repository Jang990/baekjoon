import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        PriorityQueue<Long> pq = new PriorityQueue<>();
        long[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong).toArray();
        for (int i = 0; i < n; i++) {
            pq.offer(arr[i]);
        }
        br.close();

        for (int i = 0; i < m; i++) {
            long result = pq.poll() + pq.poll();
            pq.offer(result);
            pq.offer(result);
        }

        System.out.println(pq.stream().mapToLong(Long::valueOf).sum());
    }
}
