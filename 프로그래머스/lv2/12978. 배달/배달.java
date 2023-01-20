import java.util.Arrays;
class Solution {
    static int[][] map;
	static int[] shortCut;
	static boolean[] visited;
	static int Max;
	public static int solution(int N, int[][] road, int K) {
        Max = K;
        map = new int[N+1][N+1];
        shortCut = new int[N+1];
        visited = new boolean[N+1];
        
        for (int i = 0; i < road.length; i++) {
        	int time;
        	if(map[road[i][0]][road[i][1]] != 0)
        		time = Math.min(map[road[i][0]][road[i][1]], road[i][2]);
        	else
        		time = road[i][2];
        	
        	map[road[i][0]][road[i][1]] = time;
			map[road[i][1]][road[i][0]] = time;
		}
        
        visited[1] = true;
        BFS(1, 0);
        
        return (int) Arrays.stream(shortCut).filter(n -> n != 0).count() + 1;
    }
	
	private static void BFS(int now, int time) {
		if(time > Max)
			return;
		
		shortCut[now] = time;
	
		for (int i = 1; i < map[now].length; i++) {
            if(map[now][i] == 0)
				continue;
			if(visited[i])
				continue;
			if(shortCut[i] != 0 && shortCut[i] < shortCut[now] + map[now][i])
				continue;
			
			visited[i] = true;
			BFS(i, shortCut[now]+map[now][i]);
			visited[i] = false;
		}
	}
}