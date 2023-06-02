import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<String> serialList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            serialList.add(br.readLine());
        }
        br.close();
        Comparator<String> rule = (s1, s2) -> {
            if (s1.length() > s2.length()) {
                return 1;
            }
            else if(s1.length() < s2.length()) {
                return -1;
            }

            int sum1 = extractNumber(s1).stream().mapToInt(Integer::valueOf).sum();
            int sum2 = extractNumber(s2).stream().mapToInt(Integer::valueOf).sum();
            if (sum1 > sum2) {
                return 1;
            } else if (sum1 < sum2) {
                return -1;
            }

            return compareMyRule(s1, s2);
        };
        serialList.sort(rule);
        StringBuilder sb = new StringBuilder();
        for (String s : serialList) {
            sb.append(s + "\n");
        }

        System.out.println(sb.toString());
    }

    private static int compareMyRule(String s1, String s2) {
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (c1 == c2) {
                continue;
            }

            if(c1 > c2) {
                return 1;
            }
            else {
                return -1;
            }
        }

        return 0;
    }

    private static List<Integer> extractNumber(String str) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i)-'0';
            if (0 <= c && c < 10) {
                list.add(c);
            }
        }

        return list;
    }
}
