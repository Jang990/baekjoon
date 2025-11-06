import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        PriorityQueue<Range> pq = new PriorityQueue<>(Comparator.comparing(r -> r.end));
        for(int i = 0; i < targets.length; i++) 
            pq.offer(new Range(targets[i][0], targets[i][1]));
        
        int currentEnd = 0;
        while(!pq.isEmpty()) {
            currentEnd = pq.poll().end;
            while(!pq.isEmpty() && pq.peek().start < currentEnd)
                pq.poll();
            answer++;
        }
        return answer;
    }
    
    static class Range {
        int start, end;
        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}