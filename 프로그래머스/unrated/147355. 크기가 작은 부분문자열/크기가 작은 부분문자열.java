class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        int c = p.charAt(0) - '0';
        int len = p.length();
        
        for (int i = 0; i <= t.length() - len; i++) {
			if(c < (int)(t.charAt(i) - '0'))
				continue;
			
			if(Long.parseLong(p) >= Long.parseLong(t.substring(i, i+len)))
				answer++;
		}
        
        return answer;
    }
}