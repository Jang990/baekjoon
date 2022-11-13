import java.util.*;
import java.io.*;

public class Main {

	static int[][] arr;
	static int[] moonduri;
	static boolean[] visited;
	static int N;
	static int Min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.valueOf(br.readLine());
		arr = new int[N][3];
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		moonduri = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		Rec(0, 0, 0, 0, 0);
		
		System.out.println(Min);
		
		br.close();
	}

	private static void Rec(int idx, int start, int r, int g, int b) {
		if(idx > 7) {
			return;
		}
		
		if(idx > 1) {
			int avgR = r / idx;
			int avgG = g / idx;
			int avgB = b / idx;
			
			Min = Math.min(Min, Math.abs(moonduri[0] - avgR) + Math.abs(moonduri[1] - avgG) + Math.abs(moonduri[2] - avgB)); 
		}
		
		for (int i = start; i < arr.length; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			Rec(idx+1, i, r + arr[i][0], g + arr[i][1], b + arr[i][2]);
			visited[i]= false;
		}
	}

}