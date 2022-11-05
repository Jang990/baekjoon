import java.io.*;
import java.util.*;

public class Main {
	
	static Map<Character, Integer> map = new HashMap<>();
	static Stack<Character> st = new Stack<>();
	static String output = "";
	static String s = "";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		for (int i = 0; i < str.length(); i++) {
			map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
		}
		
		int cnt = (int)map.values().stream().filter(n -> n%2 != 0).count();
		
		boolean flag = false;
		if(str.length() % 2 == 0) {
			if(cnt != 0) 
				flag = true;
		}
		else {
			if(cnt != 1)
				flag = true;
		}
		
		if(flag) {
			System.out.println("I'm Sorry Hansoo");
			return;
		}
		
		map.keySet().stream().sorted().forEach((k) -> {
			if(map.get(k)%2 == 1)
				s += k;
			for (int i = 0; i < map.get(k)/2; i++) {
				output += k;
				st.add(k);
			}
		});
		
		output += s;
		while(!st.isEmpty()) {
			output += st.pop();
		}
		System.out.println(output);
		
		
		
		br.close();
	}

}