import java.util.PriorityQueue;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            pq.offer(scoville[i]);
        }

        int result = 0;
        while (pq.size() >= 2 && pq.peek() < K) {
            pq.offer(pq.poll() + pq.poll() * 2);
            result++;
        }

        if(pq.peek() < K)
            return -1;
        return result;
    }
}