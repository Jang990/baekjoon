import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        StringTokenizer st;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int maxDeadLine = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.valueOf(st.nextToken());
            int cup = Integer.valueOf(st.nextToken());
            maxDeadLine = Math.max(maxDeadLine, deadline);

            List<Integer> cupList = map.get(deadline);
            if (cupList == null) {
                List<Integer> value = new ArrayList<>();
                map.put(deadline, value);
                cupList = value;
            }
            cupList.add(cup);
        }
        br.close();


        PriorityQueue<Integer> qu = new PriorityQueue<>(Comparator.reverseOrder());
        int result = 0;
        for (int i = N; i > 0; i--) {
            List<Integer> list = map.get(i);
            if (list != null) {
                qu.addAll(list);
            }

            if (qu.isEmpty()) {
                continue;
            }
            result += qu.poll();
        }

        System.out.println(result);
    }
}
