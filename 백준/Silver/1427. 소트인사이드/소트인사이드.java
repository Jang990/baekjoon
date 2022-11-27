import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		br.close();
		
		List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < str.length(); i++) {
			list.add(str.charAt(i) - '0');
			
		}
		
		StringBuffer sb = new StringBuffer();
		list.stream().sorted(Comparator.reverseOrder()).forEach((n) -> sb.append(n));
		System.out.println(sb);
	}
}
