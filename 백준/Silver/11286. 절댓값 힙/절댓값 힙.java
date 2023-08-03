import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> qu = new PriorityQueue<>((n1, n2) -> {
            Integer absN1 = Math.abs(n1);
            Integer absN2 = Math.abs(n2);
            if (!absN1.equals(absN2)) {
                return absN1.compareTo(absN2);
            }

            return n1.compareTo(n2);
        });

        int N = Integer.valueOf(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int order = Integer.valueOf(br.readLine());
            if (order == 0) {
                Integer poll = qu.poll();
                if (poll == null) {
                    sb.append("0\n");
                }
                else {
                    sb.append(poll+"\n");
                }
                continue;
            }
            qu.add(order);
        }
        br.close();

        System.out.println(sb);
    }
}

