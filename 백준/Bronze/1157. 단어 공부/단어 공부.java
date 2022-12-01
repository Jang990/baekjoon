import java.io.*;
import java.util.*;

public class Main {
	static Map<Character, Integer> map = new HashMap<>();
	static String s = "";
	static int Max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine().toUpperCase();
		
		int num;
		for (int i = 0; i < str.length(); i++) {
			num = map.getOrDefault(str.charAt(i), 0) + 1;
			Max = Math.max(Max, num);
			map.put(str.charAt(i), num);
		}
		
		
		map.forEach((k, v) -> {
			if(v == Max) 
				s += k;
		});
		
		if(s.length() == 1)
			System.out.println(s);
		else
			System.out.println("?");
		
		br.close();
	}

}