import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int sum = 0;
        for (int i = 0; i < enemy.length; i++) {
            int now = enemy[i];
            sum += now;
            pq.offer(now);

            while (!pq.isEmpty() && sum > n && k != 0) {
                sum -= pq.poll();
                k--;
            }
            
            if(sum > n)
                return i;
        }

        return enemy.length;
    }
}