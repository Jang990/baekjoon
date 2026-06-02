import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public static int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> sum = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            sum.put(genres[i], sum.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<String> rank = sum.keySet().stream()
                .sorted(Comparator.comparingInt(sum::get).reversed())
                .collect(Collectors.toList());

        List<Integer> result = new ArrayList<>();
        for (String genre : rank) {
            int top1Idx = -1, top2Idx = -1;
            int top1Play = -1, top2Play = -1;
            for (int i = 0; i < genres.length; i++) {
                if(!genres[i].equals(genre))
                    continue;
                if (plays[i] > top1Play) {
                    top2Play = top1Play;
                    top2Idx = top1Idx;

                    top1Play = plays[i];
                    top1Idx = i;
                    continue;
                }

                if (plays[i] > top2Play) {
                    top2Play = plays[i];
                    top2Idx = i;
                }
            }
            result.add(top1Idx);
            if(top2Idx != -1)
                result.add(top2Idx);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

}