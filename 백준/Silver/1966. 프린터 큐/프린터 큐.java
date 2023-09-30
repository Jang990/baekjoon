import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.valueOf(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < testCase; i++) {
            String[] arr = br.readLine().split(" ");
            int size = Integer.valueOf(arr[0]);
            int targetIdx = Integer.valueOf(arr[1]);
            Queue<Integer> qu = new LinkedList<>(Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf).boxed().collect(Collectors.toList()));

            int cnt = 0;
            while (!qu.isEmpty()) {
                int max = qu.stream().max(Integer::compareTo).get();
                if (qu.peek() == max) {
                    cnt++;
                    if (targetIdx == 0) {
                        break;
                    }
                } else {
                    qu.add(qu.peek());
                    if (targetIdx == 0) {
                        targetIdx = qu.size()-1;
                    }
                }
                qu.poll();
                targetIdx--;
            }
            sb.append(cnt + "\n");
        }
        br.close();

        System.out.println(sb);
    }
}
