import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M;
	static Map<String, Integer> mapN;
	private static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		
		mapN = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			mapN.put(br.readLine(), 0);
		}
		
		for (int i = 0; i < M; i++) {
			if(mapN.containsKey(br.readLine())) cnt++;
		}
		
		System.out.println(cnt);
		
		br.close();
	}

}
