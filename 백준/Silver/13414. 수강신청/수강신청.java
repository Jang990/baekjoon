import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.valueOf(st.nextToken());
        int L = Integer.valueOf(st.nextToken());
        Map<String, Integer> numAndOrder = new HashMap<>();
        Map<Integer, String> orderAndNum = new HashMap<>();
        for (int i = 1; i <= L; i++) {
            String studentNumber = br.readLine();
            Integer existingOrder = numAndOrder.get(studentNumber);
            if (existingOrder != null) {
                orderAndNum.remove(existingOrder);
            }
            numAndOrder.put(studentNumber, i);
            orderAndNum.put(i, studentNumber);
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        int acceptedSize = 0;
        for (int i = 1; i <= L; i++) {
            String number = orderAndNum.get(i);
            if (number == null) {
                continue;
            }

            sb.append(number + "\n");
            acceptedSize++;

            if (acceptedSize >= K) {
                break;
            }
        }

        System.out.println(sb);

    }
}
