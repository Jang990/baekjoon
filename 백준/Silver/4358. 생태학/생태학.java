import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		Map<String, Integer> map = new HashMap<>();
		
		int cnt = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new HashMap<String, Integer>();
		String str = br.readLine();
		while(true) {
			map.put(str, map.getOrDefault(str, 0) + 1);
			cnt++;
			
			str = br.readLine();
			if(str == null || str.length() == 0) {
				break;
			}
		}
		
		List<String> keySet = new ArrayList<>(map.keySet());
		Collections.sort(keySet);
		StringBuffer sb = new StringBuffer();
		for (String string : keySet) {
			double value = (double)map.get(string) / cnt * 100;
			sb.append(string + " " + String.format("%.4f", value) + "\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}

}