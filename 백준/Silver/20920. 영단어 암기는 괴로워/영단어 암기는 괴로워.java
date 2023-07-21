import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);

        List<String> words = new ArrayList<>();
        Map<String, Integer> wordCnt = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (word.length() < M) {
                continue;
            }

            int count = wordCnt.getOrDefault(word, 0);
            if (count == 0) {
                words.add(word);
            }

            wordCnt.put(word, count + 1);
        }

        br.close();

        Comparator<String> wordComp = (s1, s2) -> {
            int s1Count = wordCnt.get(s1);
            int s2Count = wordCnt.get(s2);
            if (s1Count > s2Count) {
                return -1;
            } else if (s1Count < s2Count) {
                return 1;
            }

            int s1Length = s1.length();
            int s2Length = s2.length();
            if (s1Length > s2Length) {
                return -1;
            } else if (s1Length < s2Length) {
                return 1;
            }

            return s1.compareTo(s2);
        };
        words.sort(wordComp);

        StringBuilder sb = new StringBuilder();
        words.forEach(s -> sb.append(s + "\n"));
        System.out.println(sb);
    }
}
