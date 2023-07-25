import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        String base = br.readLine();
        Map<Character, Integer> baseMap = new HashMap<>();
        for (int i = 0; i < base.length(); i++) {
            char key = base.charAt(i);
            baseMap.put(key, baseMap.getOrDefault(key,0) + 1);
        }

        // 하나의 문자를 빼거나, 바꾸거나 = 비슷함
        int result = 0;
        for (int i = 0; i < n-1; i++) {
            String line = br.readLine();
            if (base.length() - 1 > line.length() || line.length() > base.length() + 1) {
                continue;
            }

            Map<Character, Integer> targetMap = new HashMap<>();
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                targetMap.put(c, targetMap.getOrDefault(c,0) + 1);
            }

            if(isSimilarWord(baseMap, targetMap)) {
                result++;
            }
        }
        br.close();

        System.out.println(result);
    }

    private static boolean isSimilarWord(Map<Character, Integer> baseMap, Map<Character, Integer> targetMap) {
        int wrongSpe = 0;
        int difference = 0;

        for (Character c : targetMap.keySet()) {
            int baseCnt = baseMap.getOrDefault(c, 0);
            int targetCnt = targetMap.getOrDefault(c, 0);

            if (baseCnt == targetCnt) {
                continue;
            }

            if (baseCnt == 0) {
                wrongSpe += targetCnt;
            }
            else {
                difference += Math.abs(baseCnt - targetCnt);
            }
        }

        HashSet<Character> cloneBaseKeySet = new HashSet<>(baseMap.keySet());
        cloneBaseKeySet.removeAll(targetMap.keySet());

        for (Character c : cloneBaseKeySet) {
            difference += baseMap.get(c);
        }

        if (wrongSpe > 1) {
            return false;
        }

        if (difference > 1 && (difference != 2 || wrongSpe != 0)) {
            return false;
        }

        return true;
    }
}
