import java.util.PriorityQueue;
class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < score.length; i++) {
        	if(heap.size() < k)
        		heap.add(score[i]);
        	else {
        		if(score[i] > heap.peek()) {
        			heap.poll();
        			heap.add(score[i]);
        		}
        	}
        	
        	answer[i] = heap.peek();
		}
        
        return answer;
    }
}