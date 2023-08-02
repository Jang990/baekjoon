import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        Map<Integer, List<Integer>> map = new HashMap<>();

        StringTokenizer st;
        int maxDeadLine = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int money = Integer.valueOf(st.nextToken());
            int deadline = Integer.valueOf(st.nextToken());

            List<Integer> deadlineList = map.get(deadline);
            if (deadlineList == null) {
                deadlineList = new ArrayList<>();
                deadlineList.add(money);
                map.put(deadline, deadlineList);
            }
            else {
                deadlineList.add(money);
            }

            maxDeadLine = Math.max(deadline, maxDeadLine);
        }
        br.close();

        List<Integer> now = new ArrayList<>();
        int result = 0;
        for (int day = maxDeadLine; day > 0; day--) {
            List<Integer> deadLineList = map.get(day);

            if (deadLineList != null) {
                now.addAll(deadLineList);
                now = now.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            }

            if (now.isEmpty()) {
                continue;
            }

            result += now.get(0);
            now.remove(0);
        }
        System.out.println(result);
    }
}
