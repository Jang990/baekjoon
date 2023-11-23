import java.util.*;
class Solution {
    public static String solution(String[] survey, int[] choices) {
        Map<String, Integer> map = new HashMap<>();
        initMap(map);
        for (int i = 0; i < survey.length; i++) {
            if(choices[i] == 4)
                continue;
            String[] type = survey[i].split("");
            if (choices[i] < 4) {
                int sum = map.get(type[0]) + 4-choices[i];
                map.put(type[0], sum);
            } else {
                int sum = map.get(type[1]) + choices[i] - 4;
                map.put(type[1], sum);
            }
        }

        return createString(map);
    }

    private static String createString(Map<String, Integer> map) {
        StringBuilder sb = new StringBuilder();

        if (map.get("R") >= map.get("T"))
            sb.append("R");
        else
            sb.append("T");

        if (map.get("C") >= map.get("F"))
            sb.append("C");
        else
            sb.append("F");

        if (map.get("J") >= map.get("M"))
            sb.append("J");
        else
            sb.append("M");

        if (map.get("A") >= map.get("N"))
            sb.append("A");
        else
            sb.append("N");

        return sb.toString();
    }

    private static void initMap(Map<String, Integer> map) {
        map.put("R", 0);
        map.put("T", 0);
        map.put("C", 0);
        map.put("F", 0);
        map.put("J", 0);
        map.put("M", 0);
        map.put("A", 0);
        map.put("N", 0);
    }
}