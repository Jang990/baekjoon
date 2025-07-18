import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pqLeft = new PriorityQueue<>(Comparator.comparing(Integer::intValue).reversed());
        PriorityQueue<Integer> pqRight = new PriorityQueue<>();

        int mid = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        sb.append(mid).append("\n");
        for (int i = 1; i < N; i++) {
            int val = Integer.parseInt(br.readLine());
            if(val < mid) pqLeft.offer(val);
            else pqRight.offer(val);

            int diff = pqLeft.size() - pqRight.size();
            if (diff > 0) {
                pqRight.offer(mid);
                mid = pqLeft.poll();
            } else if(diff < -1) {
                pqLeft.offer(mid);
                mid = pqRight.poll();
            }
            sb.append(mid).append("\n");
        }
        br.close();

        System.out.println(sb);
    }
}
