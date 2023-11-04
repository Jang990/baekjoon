import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> qu = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            for (int j = 0; j < n; j++) {
                if (qu.size() < n) {
                    qu.offer(arr[j]);
                    continue;
                }

                if(arr[j] < qu.peek())
                    continue;

                qu.poll();
                qu.offer(arr[j]);
            }
        }
        br.close();

        System.out.println(qu.peek());
    }
}
