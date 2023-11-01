import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();

        int n = Integer.valueOf(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num = Integer.valueOf(br.readLine());
            if (!right.isEmpty() && right.peek() < num)
                right.offer(num);
            else
                left.offer(num);

            if (!right.isEmpty() && right.size() > left.size()) {
                left.offer(right.poll());
            }

            if (!left.isEmpty() && right.size() + 1 < left.size()) {
                right.offer(left.poll());
            }

            sb.append(left.peek()+"\n");
        }
        br.close();

        System.out.println(sb);
    }

}
