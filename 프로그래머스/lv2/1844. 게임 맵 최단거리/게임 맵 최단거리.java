import java.util.*;
class Solution {
    static int[][] step;
	public static int solution(int[][] maps) {
        step = new int[maps.length][maps[0].length];
        step[0][0] = 1;
        
        int[] dirX = {0,0,1,-1};
        int[] dirY = {1,-1,0,0};
        int nowX, nowY;
        Queue<Point> qu = new LinkedList<>();
        qu.offer(new Point(0, 0));
        while(!qu.isEmpty()) {
        	Point p = qu.poll();
        	for (int i = 0; i < dirY.length; i++) {
        		nowX = p.x + dirX[i];
        		nowY = p.y + dirY[i];
				if(!rangeCheck(nowX, nowY) || maps[nowY][nowX] == 0)
					continue;
				
				if(step[nowY][nowX] != 0 && step[nowY][nowX]-1 <= step[p.y][p.x])
					continue;
				
				step[nowY][nowX] = step[p.y][p.x]+1;
				qu.offer(new Point(nowX, nowY));
			}
        }
        
        int answer =step[step.length-1][step[0].length-1]; 
        if(answer == 0)
        	return -1;
        else
        	return answer;
    }
	
	private static boolean rangeCheck(int nowX, int nowY) {
		return (0 <= nowX && nowX < step[0].length && 0 <= nowY && nowY < step.length);
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}