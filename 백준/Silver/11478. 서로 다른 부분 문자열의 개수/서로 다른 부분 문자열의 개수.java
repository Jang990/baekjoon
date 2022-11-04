import java.util.*;
import java.io.*;

public class Main {
	static Set<String> set = new HashSet<>();
	static String str;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str = br.readLine();
		
		for (int i = 0; i < str.length(); i++) {
			search(i);
		}
		
		System.out.println(set.size());
		
		br.close();
	}

	private static void search(int len) {
		for (int i = 0; i < str.length() - len; i++) {
			set.add(str.substring(i, i+len+1));
		}
	}
}