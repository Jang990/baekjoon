import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        Queue<String> qu = new LinkedList<>();
        // qu의 peek가 아닌 차가 먼저 나왔다 => 추월했다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            qu.offer(br.readLine());
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            String output = br.readLine();
            if (output.equals(qu.peek())) {
                qu.poll();
                continue;
            }
            qu.remove(output);
            result++;
        }
        System.out.println(result);
        br.close();
    }
}
