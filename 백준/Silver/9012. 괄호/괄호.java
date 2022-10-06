import java.util.*;

public class Main {
	static StringBuffer sb = new StringBuffer();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		int testCase = Integer.parseInt(sc.nextLine());
		
		for (int i = 0; i < testCase; i++) {
			if(checkStack()) {
				sb.append("YES\n");
			}
			else {
				sb.append("NO\n");
			}
		}
		
		System.out.println(sb);
		
		sc.close();
	}

	private static boolean checkStack() {
		Stack<Character> st = new Stack<>();
		String s = sc.nextLine();
		
		for (int j = 0; j < s.length(); j++) {
			char c = s.charAt(j);
			if(c == '(')
				st.push(c);
			else if(c == ')') {
				if(st.isEmpty()) {
					return false;
				}
				st.pop();
			}
		}
		if(st.isEmpty()) 
			return true;
		else 
			return false;
	}

}