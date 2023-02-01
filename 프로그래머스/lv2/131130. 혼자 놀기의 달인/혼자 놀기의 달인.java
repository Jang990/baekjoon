import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
class Solution {
    static boolean[] visited;
	static int[] map;
	static List<Integer> group;
	public static int solution(int[] cards) {
        map = cards;
        visited = new boolean[cards.length];
        group = new ArrayList<>();
        for (int i = 0; i < cards.length; i++) {
			if(visited[i])
				continue;
			dfs(i, 0);
		}
        
        int[] array = group.stream().sorted(Comparator.reverseOrder()).mapToInt(Integer::valueOf).toArray();
        	
        return (array.length > 1) ? array[0] * array[1] : 0;
    }
	private static void dfs(int i, int cnt) {
		if(visited[i]) {
			group.add(cnt);
			return;
		}
		
		visited[i] = true;
		dfs(map[i]-1, cnt+1);
	}
}