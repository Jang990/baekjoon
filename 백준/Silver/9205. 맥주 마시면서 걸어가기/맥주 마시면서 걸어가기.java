import java.io.*;
import java.util.*;

public class Main {
	static int n,sx, sy, ex,ey; 
	static List<int[]> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		
		int t = Integer.valueOf(br.readLine());
		int x,y;
		
		
		for (int i = 0; i < t; i++) {
			n = Integer.valueOf(br.readLine());
			list = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			sx = Integer.valueOf(st.nextToken());
			sy = Integer.valueOf(st.nextToken());
			
			
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.valueOf(st.nextToken());
				y = Integer.valueOf(st.nextToken());
				list.add(new int[] {x, y});
			}
			
			st = new StringTokenizer(br.readLine());
			ex = Integer.valueOf(st.nextToken());
			ey = Integer.valueOf(st.nextToken());
			
			if(BFS()) 
				sb.append("happy\n");
			else
				sb.append("sad\n");
		}
		
		System.out.println(sb);
		
		br.close();
	}

	private static boolean BFS() {
		Queue<int[]> qu = new LinkedList<>();
		boolean[] visited = new boolean[n];
		qu.offer(new int[] {sx, sy});
		
		int qx, qy, nx,ny;
		int[] node;
		while(!qu.isEmpty()) {
			node = qu.poll();
			qx = node[0];
			qy = node[1];
			if(Math.abs(qx - ex) + Math.abs(qy - ey) <= 1000) {
				return true;
			}
			
			for (int i = 0; i < n; i++) {
				if(!visited[i]) {
					nx = list.get(i)[0];
					ny = list.get(i)[1];
					if(Math.abs(qx - nx) + Math.abs(qy - ny) <= 1000) {
						qu.offer(new int[] {nx, ny});
						visited[i] = true;
					}
				}
			}
			
		}
		return false;
	}

}