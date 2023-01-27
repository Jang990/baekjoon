class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        answer[0] = -1;
        for (int i = 1; i < answer.length; i++) {
        	int idx = s.substring(0, i).lastIndexOf(s.charAt(i));
        	if(idx == -1)
        		answer[i] = -1;
        	else 
        		answer[i] = i - s.substring(0, i).lastIndexOf(s.charAt(i));
		}
        
        return answer;
    }
}