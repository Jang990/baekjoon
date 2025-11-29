import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int[] snows = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int snow : snows) {
            pq.offer(snow);
        }
        br.close();

        int result = 0;
        while (!pq.isEmpty()) {
            if (pq.size() == 1) {
                result += pq.poll();
                break;
            }

            int snow1 = pq.poll();
            int snow2 = pq.poll();
            if (snow1 == snow2) {
                result += snow1;
                continue;
            }
            int current = Math.min(snow1, snow2);
            result += current;
            if(current < snow1)
                pq.offer(snow1 - current);
            else
                pq.offer(snow2 - current);
        }

        if(result > 1440)
            System.out.println(-1);
        else
            System.out.println(result);
    }
}
