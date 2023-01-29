class Solution {
    public int solution(String s) {
        String[] numStr = {"zero","one","two","three","four",
        		"five","six","seven","eight","nine"};
        
        for (int i = 0; i < numStr.length; i++) {
        	s = s.replaceAll(numStr[i], i+"");
		}
        
        return Integer.valueOf(s);
    }
}