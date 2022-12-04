import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		List<String > output = new ArrayList<>();
		
		for (int i = 0; i < str.length(); i++) {
			output.add(str.substring(i));
		}
		
		StringBuffer sb = new StringBuffer(); 
		output.stream().sorted().forEach(s -> sb.append(s + "\n"));
		System.out.println(sb);
		
		br.close();
	}
}
