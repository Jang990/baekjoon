import java.io.*;
import java.util.*;

public class Main {

	static int[][] graph = new int[5][5];
	static int[] arr = new int[6];
	static Set<Integer> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 5; i++) {
			graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				BFS(0, new Node(j, i));
			}
		}
		
		System.out.println(set.size());
		
		br.close();
	}
	
	private static void BFS(int idx, Node node) {
		if(idx == 6) {
			String str = "";
			for (int i = 0; i < arr.length; i++) {
				str += arr[i];
			}
			set.add(Integer.valueOf(str));
			return;
		}
		
		int[] dirX = {0,0,1,-1};
		int[] dirY = {1,-1,0,0};
		int nowX, nowY;
		for (int i = 0; i < dirY.length; i++) {
			nowX = node.x + dirX[i];
			nowY = node.y + dirY[i];
			
			if(rangeCheck(nowX, nowY)) {
				arr[idx] = graph[nowY][nowX];
				BFS(idx+1, new Node(nowX, nowY));
			}
		}
		
	}

	private static boolean rangeCheck(int nowX, int nowY) {
		return (5 > nowX && nowX >= 0 && 5 > nowY && nowY >= 0);
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}