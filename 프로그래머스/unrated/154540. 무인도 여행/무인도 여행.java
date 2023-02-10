import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
class Solution {
    static boolean[][] visited;
	static int[][] map;
	public static int[] solution(String[] maps) {
		visited = new boolean[maps.length][maps[0].length()];
		map = new int[maps.length][maps[0].length()];
		List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < maps.length; i++) {
			for (int j = 0; j < maps[i].length(); j++) {
				char c = maps[i].charAt(j);
				if('1' <= c && c <= '9')
					map[i][j] = c - '0';
				else
					map[i][j] = 0;
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(visited[i][j] || map[i][j] == 0)
					continue;
				
				list.add(BFS(new Point(j, i)));
			}
		}
		
        int[] answer;
        if(list.size() > 0)
        	answer = list.stream().sorted().mapToInt(Integer::valueOf).toArray();
        else
        	answer = new int[] {-1};
        return answer;
    }
	
	private static int BFS(Point start) {
		Queue<Point> qu = new LinkedList<>();
		qu.offer(start);
		visited[start.y][start.x] = true;
		int sum = map[start.y][start.x];
		Point p;
		int[] dirX = {0,0,1,-1};
		int[] dirY = {1,-1,0,0};
		int nowX, nowY;
		while(!qu.isEmpty()) {
			p = qu.poll();
			for (int i = 0; i < dirY.length; i++) {
				nowX = p.x + dirX[i];
				nowY = p.y + dirY[i];
				if(!checkRange(nowX, nowY) || visited[nowY][nowX] || map[nowY][nowX] == 0)
					continue;
				
				sum += map[nowY][nowX];
				visited[nowY][nowX] = true;
				qu.offer(new Point(nowX, nowY));
			}
		}
		
		return sum;
	}
	
	static boolean checkRange(int x, int y) {
		return (0 <= x && x < map[0].length && 0 <= y && y < map.length);
	}
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}