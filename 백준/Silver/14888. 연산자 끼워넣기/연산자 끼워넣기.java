import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] nums;
	static int[] ops;
	static int[] opsForCalc;
	static boolean[] visited;
	static final int PLUS = 0, MINUS = 1, MUTI = 2, DIVI = 3;
	static int Max = Integer.MIN_VALUE, Min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.valueOf(br.readLine());
		nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.valueOf(st.nextToken());
		}
		
		ops = new int[4];
		int opsNum = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			ops[i] = Integer.valueOf(st.nextToken());
			opsNum += ops[i];
		}
		br.close();
		
		
		opsForCalc = new int[opsNum];
		visited = new boolean[opsNum];
		int idx = 0;
		for (int i = 0; i < ops.length; i++) {
			for (int j = 0; j < ops[i]; j++) {
				opsForCalc[idx] = i;
				idx++;
			}
		}
		
		DFS(0, nums[0]);
		
		System.out.println(Max + "\n" + Min);
	}

	private static void DFS(int idx, int result) {
		if(idx == N-1) {
			Max = Math.max(Max, result);
			Min = Math.min(Min, result);
			return;
		}
		
		int afterResult = 0;
		for (int i = 0; i < opsForCalc.length; i++) {
			if(visited[i]) 
				continue;
			
			visited[i] = true;
			switch (opsForCalc[i]) {
				case PLUS:
					afterResult = result + nums[idx+1];
					break;
				case MINUS:
					afterResult = result - nums[idx+1];
					break;
				case MUTI:
					afterResult = result * nums[idx+1];
					break;
				case DIVI:
					afterResult = (int)(result / nums[idx+1]);
					break;
			}
			
			DFS(idx+1, afterResult);
			visited[i] = false;
		}
	}
	
	

}
