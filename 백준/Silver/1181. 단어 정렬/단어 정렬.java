import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.valueOf(br.readLine());
		List<String> list = new ArrayList<>();
		Set<String> output = new LinkedHashSet<>();
		String str = "";
		
		
		for (int i = 0; i < n; i++) {
			str = br.readLine(); 
			list.add(str);
		}
		br.close();
		
		
		list = list.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
		
		int idx = 1, length = 0;
		while(list.size() != 1) {
			length = list.get(0).length();
			
			if(list.get(idx).length() != length) {
				List<String> sortedList = new ArrayList<>();
				for (int i = 0; i < idx; i++) {
					sortedList.add(list.get(0));
					list.remove(0);
				}
				sortedList.stream().sorted().forEach(output::add);
				idx = 0;
			}
			idx++;
			if(idx == list.size()) 
				break;
		}
		
		list.stream().sorted().forEach(output::add);
		
		
		StringBuffer sb = new StringBuffer();
		for (String string : output) {
			sb.append(string + "\n");
		}
		System.out.println(sb);
	}

}