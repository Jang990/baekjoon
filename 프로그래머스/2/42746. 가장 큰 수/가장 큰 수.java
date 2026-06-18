import java.util.*;

class Solution {
    public static String solution(int[] numbers) {
        PriorityQueue<String> pq = new PriorityQueue<>(stringComparator);

        for (int number : numbers) {
            pq.offer(String.valueOf(number));
        }
        
        if(pq.peek().equals("0"))
            return "0";
        
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll());
        }
        return sb.toString();
    }



    


    static Comparator<String> stringComparator = (s1, s2) -> {
        String s1s2 = s1.concat(s2);
        String s2s1 = s2.concat(s1);
        return s2s1.compareTo(s1s2);
    };
}