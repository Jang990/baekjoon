import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        PriorityQueue<Integer> card = new PriorityQueue();
        for (int i = 0; i < N; i++) {
            card.add(Integer.valueOf(br.readLine()));
        }
        br.close();
        int result = 0;
        while (card.size() > 1) {
            int n1 = card.poll();
            int n2 = card.poll();
            int sum = n1 + n2;
            card.offer(sum);
            result += sum;
        }
        System.out.println(result);
    }
}
