import java.io.*; 
import java.util.*;

public class Main {

	static Set<Integer> set = new HashSet<>();
	static int N,M;
	static int[] in, out;
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		while(st.hasMoreTokens()) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		in = set.stream().mapToInt(Integer::intValue).sorted().toArray();
		out = new int[M];
		
		Rec(0);
		System.out.println(sb);
		br.close();
	}

	private static void Rec(int idx) {
		if(idx == M) {
			for (int i = 0; i < out.length; i++) {
				sb.append(out[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < in.length; i++) {
			out[idx] = in[i];
			Rec(idx+1);
		}
	}

}