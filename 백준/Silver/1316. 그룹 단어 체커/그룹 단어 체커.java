import java.io.*;
import java.util.*;

public class Main {
	
	static Set<Character> set;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		
		int idx = 0, cnt = N;
		String str;
		char c;
		while(idx != N) {
			str = br.readLine();
			set = new HashSet<>();
			set.add(str.charAt(0));
			
			
			for (int i = 1; i < str.length(); i++) {
				c = str.charAt(i);
				if(str.charAt(i-1) == c) 
					continue;
				if(set.contains(c)) {
					cnt--;
					break;
				}
				set.add(c);
			}
			set = new HashSet<>();
			idx++;
		}
		
		System.out.println(cnt);
		
		br.close();
	}

}