class Solution {
    public static int solution(int n) {
        int answer = n+1, cnt = checkBit(n);
        
        
        int answerCnt;
        while(true) {
        	answerCnt = checkBit(answer);
        	if(answerCnt == cnt)
        		break;
        	answer++;
        }
        return answer;
    }
	
	static int checkBit(int n) {
		int cnt = 0;
		String s = Integer.toBinaryString(n);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c == '1')
				cnt++;
		}
		return cnt;
	}
}