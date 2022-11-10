import java.util.*;
import java.io.*;

public class Main {

	static int N, endStation;
	static List<Set<Integer>> list = new ArrayList<>();
	static int[][] map;
	static boolean[] visited;
	static int Min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		N = Integer.valueOf(br.readLine());
		map = new int[N][N];
		
		StringTokenizer st;
		int n;
		for (int i = 0; i < N; i++) {
			Set<Integer> set = new HashSet<>();
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while(st.hasMoreTokens()) {
				n = Integer.valueOf(st.nextToken());
				set.add(n);
				for (int j = 0; j < list.size(); j++) {
					if(list.get(j).contains(n)) {
						map[j][list.size()] = 1;
						map[list.size()][j] = 1;
					}
				}
			}
			list.add(set);
		}
		
		endStation = Integer.valueOf(br.readLine());
		br.close();
		
	
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).contains(0)) {
				if(list.get(i).contains(endStation)) {
					Min = 0;
					break;
				}
				visited = new boolean[N];
				Min = Math.min(Min, BFS(i));
			}
		}
		
		if(Min == Integer.MAX_VALUE) 
			System.out.println(-1);
		else 
			System.out.println(Min);
		
	}

	private static int BFS(int idx) {
		Queue<Integer> qu = new LinkedList<>();
		qu.offer(idx);
		visited[idx] = true;
		
		int n, cnt = 0;
		while(!qu.isEmpty()) {
			n = qu.poll();
			for (int i = 0; i < map[n].length; i++) {
				if(!visited[i] && map[n][i] == 1) {
					qu.offer(i);
					visited[i] = true;
					if(list.get(i).contains(endStation)) return cnt+1;
				}
			}
			cnt++;
		}
		
		return Integer.MAX_VALUE;
	}

}