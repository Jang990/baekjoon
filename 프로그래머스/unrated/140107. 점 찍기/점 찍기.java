class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        int a = (int)(d/k), b = 0;
        
        while(true) {
        	answer += a+1;
        	b++;
        	
        	if(b*k > d)
        		break;
        	
        	
        	while(true) {
        		double length = Math.sqrt(Math.pow(a*k, 2) + Math.pow(b*k, 2));
        		if(length <= d) 
        			break;
        		else {
        			a--;
        		}
        	}
        	
        	if(a < 0)
        		break;
        }
        
        return answer;
    }
}