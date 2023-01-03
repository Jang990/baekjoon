class Solution {
    public String solution(int n, int t, int m, int p) {
                String answer = "";
        
        String fullStr = "";
        int fullLen = t*m;
        
        int num = 0;
        while(fullStr.length() <= fullLen) {
        	fullStr += Integer.toString(num++, n);
        }
        for (int i = 0; i < fullStr.length(); i++) {
        	if(i % m != p-1)
        		continue;
        	
        	answer += fullStr.charAt(i);
        	if(answer.length() == t)
        		break;
		}
        
        return answer.toUpperCase();
    }
}