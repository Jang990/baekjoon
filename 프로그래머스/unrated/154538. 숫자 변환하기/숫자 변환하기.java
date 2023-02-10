import java.util.LinkedList;
import java.util.Queue;
class Solution {
    static int[] visited = new int[1000001];
	public static int solution(int x, int y, int n) {
        if(x == y)
			return 0;
        
		Queue<Integer> qu = new LinkedList<>();
		qu.offer(x);
		visited[y] = Integer.MAX_VALUE;
		
		int now, next;
		while(!qu.isEmpty()) {
			now = qu.poll();
			
			next = now + n;
			insertQueue(qu,now,next, y);
			next = now * 2;
			insertQueue(qu,now,next, y);
			next = now * 3;
			insertQueue(qu,now,next, y);
		}
		
        return visited[y] == Integer.MAX_VALUE ? -1 : visited[y];
    }
	
	static void insertQueue(Queue<Integer> qu, int now, int next, int y) {
		if(next > y || visited[y] <= visited[now])
			return;
		if(visited[next] == 0 || visited[next] > visited[now] + 1) {
			visited[next] = visited[now] + 1;
			qu.add(next);
		}
	}
}