import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> stay = new LinkedList<>();
        Queue<Integer> bridge = new LinkedList<>();
        
        for (int i : truck_weights) {
        	stay.offer(i);
        }
        for (int i = 0; i < bridge_length; i++) {
			bridge.offer(0);
		}
        
        while(!stay.isEmpty()) {
        	int stayWeight = stay.peek();
        	int outWeigth = bridge.peek();
        	if(bridge.stream().mapToInt(Integer::valueOf).sum()+stayWeight-outWeigth <= weight) {
        		bridge.poll();
        		bridge.offer(stay.poll());
        	}
        	else {
        		bridge.poll();
        		bridge.offer(0);
        	}
        	
        	answer++;
        }
        
        return answer+bridge_length;
    }
}