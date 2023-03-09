class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 1;
        
        if(m == 1)
        	return section.length;
        
        int now = section[0];
        for (int i = 1; i < section.length; i++) {
        	if(now + m -1 >= section[i])
        		continue;
        	
        	now = section[i];
        	answer++;
		}
        
        return answer;
    }
}