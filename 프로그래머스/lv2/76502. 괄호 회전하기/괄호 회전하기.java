import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        if(s.length() == 1) 
        	return 0;
        
        for (int i = 0; i < s.length(); i++) {
        	s = s.substring(1) + s.charAt(0);
        	
        	boolean flag = true;
        	Stack<Character> st = new Stack<>();
        	for (int j = 0; j < s.length(); j++) {
				char c = s.charAt(j);
				
				if(c == '{' || c == '[' || c == '(') {
					char savedC = 'x';
					switch (c) {
					case '(':
						savedC = ')';
						break;
					case '[':
						savedC = ']';
						break;
					case '{':
						savedC = '}';
						break;
					}
					
					st.push(savedC);
					
					continue;
				}
				
				if(st.size() == 0) {
                    flag = false;
                    break;
                }
				
				
				char findValue = st.pop();
				if(c != findValue) {
					flag = false;
					break;
				}
				
			}
        	
        	if(flag && st.size() == 0)
        		answer++;
		}
        
        return answer;
    }
}