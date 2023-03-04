import java.util.Stack;
class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        Stack<Integer> cook = new Stack<>();
        int size = 0;
        
        for (int i = 0; i < ingredient.length; i++) {
        	int now = ingredient[i];
        	
        	if(size == 3 && now == 1) {
        		for (int j = 0; j < size; j++) {
					cook.pop();
				}
        		answer++;
        		
        		if(cook.isEmpty()) {
        			size = 0;
        			continue;
        		}
        		// 사이즈를 정해줘야 함.
        		else if(cook.peek() == 1) {
        			size = 1;
        		}
        		else {
        			int nextSize = cook.pop();
            		int idx = 1;
            		while(!cook.isEmpty() && nextSize - idx > 0 && nextSize - idx == cook.peek()) {
            			cook.pop();
            			idx++;
            		}
            		
            		if(nextSize == idx) 
            			size = nextSize;
            		else
            			size = 0;
            		
            		for (int j = nextSize-idx+1; j <= nextSize; j++) {
    					cook.push(j);
    				}
        		}
        		continue;
        	}
        	
        	if(size+1 == now)
        		size++;
        	else if(now == 1)
        		size = 1;
        	else
        		size = 0;
        	cook.add(now);
		}
        
        
        return answer;
    }
}