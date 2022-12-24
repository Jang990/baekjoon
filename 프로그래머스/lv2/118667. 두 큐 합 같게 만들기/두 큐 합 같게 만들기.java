import java.util.LinkedList; 
import java.util.Queue;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> que1 = new LinkedList<>();
		Queue<Integer> que2 = new LinkedList<>();
		
		long sum1 = 0, sum2 = 0;
		for (int i = 0; i < queue2.length; i++) {
			que1.offer(queue1[i]);
			sum1 += queue1[i];
			
			que2.offer(queue2[i]);
			sum2 += queue2[i];
		}
		
		int cnt = 0;
		while(sum1 != sum2) {
			if(sum1 > sum2) {
				int val = que1.poll();
				sum1 -= val;
				sum2 += val;
				que2.offer(val);
			}
			else {
				int val = que2.poll();
				sum1 += val;
				sum2 -= val;
				que1.offer(val);
			}
			
			cnt++;
			
			if(cnt > queue1.length * 3) return -1;
		}
		
        return cnt;
    }
}