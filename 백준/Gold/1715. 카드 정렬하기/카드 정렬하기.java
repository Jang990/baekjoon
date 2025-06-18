import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }
        br.close();

        int result = 0 ;
        while (!pq.isEmpty() && pq.size() > 1) {
            int n1 = pq.poll();
            int n2 = pq.poll();
            pq.offer(n1 + n2);
            result += (n1 + n2);
        }

        System.out.println(result);
    }
}
