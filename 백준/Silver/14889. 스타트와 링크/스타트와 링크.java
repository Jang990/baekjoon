import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int[][] map;
	static boolean[] visited;
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.valueOf(br.readLine());
		
		map = new int[n][n];
		visited = new boolean[n];
		min = Integer.MAX_VALUE;
		
		for (int i = 0; i < n; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		br.close();
		
		Rec(0, 0);
		
		System.out.println(min);
		
	}

	private static void Rec(int idx, int num) {
		if(idx == n/2) {
			diff();
			return;
		}
		
		for (int i = num; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				Rec(idx+1, i+1);
				visited[i] = false;
			}
		}
	}

	private static void diff() {
		int teamA = 0;
		int teamB = 0;
		for (int i = 0; i < n-1; i++) {
			for (int j =  i+1; j < n; j++) {
				if(visited[i] && visited[j]) {
					teamA += map[i][j];
					teamA += map[j][i];
				}
				else if(!visited[i] && !visited[j]) {
					teamB += map[i][j];
					teamB += map[j][i];
				}
			}
		}
		
		int nowMin = Math.abs(teamA-teamB);
		
		if(nowMin == 0) {
			System.out.println(0);
			System.exit(0);
		}
		
		min = Math.min(min, nowMin);
	}

}
