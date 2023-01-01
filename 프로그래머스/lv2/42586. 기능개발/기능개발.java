import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
		ArrayList<Integer> answer = new ArrayList<>();
        Queue<Func> qu = new LinkedList<>();
        
        for (int i = 0; i < progresses.length; i++) {
			qu.offer(new Func(progresses[i], speeds[i]));
		}
        
        while(!qu.isEmpty()) {
        	Func peek = qu.peek();
        	int workDay = (int)Math.ceil((double)(100 - peek.progress) / peek.speed);
        	qu.forEach(f -> f.work(workDay));
        	
        	int idx = 0;
        	while(peek.isSuccess()) {
        		qu.poll();
        		idx++;
        		
        		if(qu.isEmpty())
        			break;
        		
        		peek = qu.peek();
        	}
        	answer.add(idx);
        }
        
        return answer.stream().mapToInt(Integer::valueOf).toArray();
    }
	
	class Func {
		int progress;
		int speed;
		
		public Func(int progress, int speed) {
			this.progress = progress;
			this.speed = speed;
		}

		public void work(int days) {
			progress += days*speed;
		}
		
		public boolean isSuccess() {
			if(progress >= 100)
				return true;
			else
				return false;
		}
	}
}