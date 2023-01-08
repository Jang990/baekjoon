import java.util.Stack;
class Solution
{
    public int solution(String s)
    {
        if(s.length() <= 1)
			return 0;
		
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			if(stack.size() == 0) {
				stack.push(c);
				continue;
			}
			
			if(stack.peek() == c)
				stack.pop();
			else
				stack.push(c);
		}
        
        if(stack.size() == 0)
        	return 1;
        else
        	return 0;
    }
}