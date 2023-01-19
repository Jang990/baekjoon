import java.util.ArrayList;
class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        ArrayList<Integer> list = new ArrayList<>();
        k = k -1;
        long factorial = 1;
        for (int i = 0; i < n; i++) {
			list.add(i+1);
        	factorial *= i+1;
		}
        
        for (int i = 0; i < n; i++) {
        	if(k == 0) {
        		for (int number : list) {
        			answer[i] = number;
					i++;
				}
        		break;
        	}
        	
        	
        	factorial /= n-i;
        	int result = (int)(k/factorial);
        	answer[i] = list.get(result);
        	list.remove(result);
        	k %= factorial;
		}
        
        return answer;
    }
}