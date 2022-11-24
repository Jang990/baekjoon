import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<String> list = new HashSet<>();
		List<String> out = new ArrayList<>();
		int N, M;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			list.add(br.readLine());
		}
		
		String str;
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			str = br.readLine();
			if(list.contains(str)) {
				cnt++;
				out.add(str);
			}
		}
		
		out = out.stream().sorted().collect(Collectors.toList());
		
		StringBuffer sb = new StringBuffer();
		sb.append(cnt + "\n");
		for (String string : out) {
			sb.append(string + "\n");
		}
		
		System.out.println(sb);
		
		br.close();
	}

}