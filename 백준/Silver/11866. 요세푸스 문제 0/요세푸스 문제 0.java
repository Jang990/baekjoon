import java.util.*;
import java.io.*;

public class Main {
	
	static int N, K;
	static List<Integer> list = new ArrayList<>();
	static List<Integer> output = new ArrayList<>();
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		K = Integer.valueOf(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}
		
		int idx = 0;
		int num;
		while(list.size() != 0) {
			idx = (idx + K -1)%list.size();
			num = list.get(idx);
			list.remove(idx);
			output.add(num);
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("<");
		output.stream().forEach((n) -> sb.append(n + ", "));
		sb.replace(sb.length()-2, sb.length(), "");
		sb.append(">");
		System.out.println(sb);
		
		br.close();
	}


}