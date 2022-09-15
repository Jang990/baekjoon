import java.util.*;
public class Main {
	static int[][] node;
	static int[] check;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		int m = sc.nextInt();
		sc.nextLine();
		
		node = new int[n+1][n+1];
		check = new int[n+1];
		
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			node[a][b] = 1;
			node[b][a] = 1;
		}
		
		bfs(1);
		
		sc.close();
	}

	private static void bfs(int start) {
		Queue<Integer> qu = new LinkedList<>();
		int cnt = 0;
		
		qu.offer(start);
		check[start] = 1;
		
		while(!qu.isEmpty()) {
			int x = qu.poll();
			for (int i = 1; i < node[x].length; i++) {
				if(node[x][i] == 1 && check[i] != 1) {
					qu.offer(i);
					check[i] = 1;
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}

}
