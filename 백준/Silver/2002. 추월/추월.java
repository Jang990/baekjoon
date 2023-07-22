import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<String> before = new ArrayList<>();
        boolean[] overtaking = new boolean[N];
        for (int i = 0; i < N; i++) {
            before.add(br.readLine());
        }

        List<String> after = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            after.add(br.readLine());
        }
        br.close();

        before.forEach(car -> {
            int afterIdx = after.indexOf(car);
            int beforeIdx = before.indexOf(car);

            List<String> oriForwardCars = before.subList(0, beforeIdx);
            List<String> nowForwardCars = after.subList(0, afterIdx);
            for (int i = 0; i < nowForwardCars.size(); i++) {
                String now = nowForwardCars.get(i);
                int oriIdx = before.indexOf(now);
                if (overtaking[oriIdx]) {
                    continue;
                }

                if (oriForwardCars.contains(now)) {
                    continue;
                }

                overtaking[oriIdx] = true;
            }
        });

        int result = 0;
        for (int i = 0; i < overtaking.length; i++) {
            if (overtaking[i]) {
                result++;
            }
        }

        System.out.println(result);
    }
}
