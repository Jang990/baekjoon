import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int solution(String str1, String str2) {
        HashMap<String, Integer> map1 = createMap(str1.toLowerCase());
        HashMap<String, Integer> map2 = createMap(str2.toLowerCase());

        Set<String> checkedInMap1 = new HashSet<>();
        int intersection = 0, union = 0;
        for (String key : map1.keySet()) {
            if (map2.get(key) == null) {
                union += map1.get(key);
            } else {
                intersection += Math.min(map1.get(key), map2.get(key));
                union += Math.max(map1.get(key), map2.get(key));
            }
            checkedInMap1.add(key);
        }

        for (String key : map2.keySet()) {
            if(checkedInMap1.contains(key))
                continue;
            union += map2.get(key);
        }

        // map 두개를 놓는다. intersection min으로 찾아 더한다. union max로 찾아 더한다.
        if(intersection == 0 && union == 0)
            return 65536;
        return (int) ((double) intersection / union * 65536);
    }

    private static HashMap<String, Integer> createMap(String str) {
        HashMap<String, Integer> result = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            sb.append(str.charAt(i));

            if(isEng(sb.charAt(0)) && isEng(sb.charAt(1)))
                result.put(sb.toString(), result.getOrDefault(sb.toString(), 0) + 1);

            sb.deleteCharAt(0);
        }
        
        return result;
    }

    private static boolean isEng(char c) {
        return 'a' <= c && c <= 'z';
    }
}