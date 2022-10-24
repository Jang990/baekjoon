import java.io.*;

public class Main {
	
	static int n;
	static int[] inArr, outArr;
	static boolean[] visited;
	static StringBuffer sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		br.close();
		
		inArr = new int[n];
		outArr = new int[n];
		visited = new boolean[n];
		sb = new StringBuffer();
		
		for (int i = 0; i < n; i++) {
			inArr[i] = i+1;
		}
		
		Rec(0);
		
		System.out.println(sb);
		
	}

	private static void Rec(int idx) {
		if(idx == n) {
			for (int i = 0; i < outArr.length; i++) {
				sb.append(outArr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < inArr.length; i++) {
			if(!visited[i]) {
				outArr[idx] = inArr[i];
				visited[i] = true;
				Rec(idx+1);
				visited[i] = false;
			}
		}
	}

}