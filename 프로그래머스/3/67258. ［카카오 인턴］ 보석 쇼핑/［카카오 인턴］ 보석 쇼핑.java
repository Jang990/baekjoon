import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> max = new HashSet<>();
        for (int i = 0; i < gems.length; i++) {
            max.add(gems[i]);
        }

        int resultStart = 1, resultEnd = gems.length;
        int nowStart = 1, nowEnd;
        Map<String, Integer> gemCount = new HashMap<>();
        Queue<String> qu = new LinkedList<>();
        for (int i = 0; i < gems.length; i++) {
            nowEnd = i+1;
            gemCount.put(gems[i], gemCount.getOrDefault(gems[i], 0) + 1);
            qu.offer(gems[i]);
            while (gemCount.getOrDefault(qu.peek(), 0) > 1) {
                String poll = qu.poll();
                gemCount.put(poll, gemCount.get(poll)-1);
                nowStart++;
            }

            if (gemCount.keySet().size() == max.size() && resultEnd - resultStart > nowEnd - nowStart) {
                resultEnd = nowEnd;
                resultStart = nowStart;
            }
        }

        System.out.println(resultStart + "," + resultEnd);


        return new int[] {resultStart, resultEnd};
    }
}