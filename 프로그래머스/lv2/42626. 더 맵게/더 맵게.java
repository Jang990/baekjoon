import java.util.PriorityQueue;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> h = new PriorityQueue<>();
        for (int i : scoville) {
        	h.add(i);
        }
        
        while(h.peek() < K) {
            if(h.size() < 2)
        		return -1;
            
        	int h1 = h.poll();
        	int h2 = h.poll();
        	
        	h.add(h1 + h2*2);
        	answer++;
        }
        
        
        return answer;
    }
}