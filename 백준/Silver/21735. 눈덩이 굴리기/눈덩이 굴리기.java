import java.util.*;
import java.io.*;

public class Main {

	private static int N, M;
	private static int[] map, arr;
	private static int Max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		
		map = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < map.length; i++) {
			map[i] = Integer.valueOf(st.nextToken());
		}
		arr = new int[M];
		
		DFS(0, 0, 1);
		System.out.println(Max);
		br.close();
	}

	private static void DFS(int idx, int loc,int result) {
		if(idx == M || loc == N) {
			Max = Math.max(Max, result);
			return;
		}
		
		int[] dir = {1, 2};
		int nowLoc;
		for (int i = 0; i < dir.length; i++) {
			nowLoc = loc + dir[i];
			if(nowLoc >= N+1) continue;
			
			if(Math.abs(dir[i]) < 2) {
				DFS(idx+1, nowLoc, result+map[nowLoc]);
				continue;
			}
			DFS(idx+1, nowLoc, result/2+map[nowLoc]);
		}
	}

}