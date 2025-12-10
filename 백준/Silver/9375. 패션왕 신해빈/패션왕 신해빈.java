import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (testCase-- > 0) {
            int cnt = Integer.parseInt(br.readLine());
            Map<String, Integer> typeAndCnt = new HashMap<>();
            for (int i = 0; i < cnt; i++) {
                String type = br.readLine().split(" ")[1];
                typeAndCnt.put(type, typeAndCnt.getOrDefault(type, 0) + 1);
            }
            if (cnt == 0) {
                sb.append("0\n");
            } else if (typeAndCnt.keySet().size() == 1) {
                String type = typeAndCnt.keySet().stream().findFirst().get();
                sb.append(typeAndCnt.get(type)).append("\n");
            } else {
                int result = 1;
                for (String type : typeAndCnt.keySet())
                    result *= (typeAndCnt.get(type) + 1);
                result -= 1; // 알몸 제외
                sb.append(result).append("\n");
            }
        }
        br.close();

        System.out.println(sb);
    }
}
