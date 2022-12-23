class Solution {
    boolean[] visited;
	int[][] dungeonsArr;
	int Max = Integer.MIN_VALUE;
	public int solution(int k, int[][] dungeons) {
        dungeonsArr = dungeons;
        visited = new boolean[dungeons.length];
        Rec(k, 0);
        return Max;
    }

	private void Rec(int k, int idx) {
		boolean flag = true;
		for (int i = 0; i < dungeonsArr.length; i++) {
			if(!visited[i] && k >= dungeonsArr[i][0])
				flag = false;
		}
		
		if(flag) {
			Max = Math.max(Max, idx);
		}
		
		for (int i = 0; i < dungeonsArr.length; i++) {
			if(visited[i] || dungeonsArr[i][0] > k)
				continue;
			
			visited[i] = true;
			Rec(k-dungeonsArr[i][1], idx+1);
			visited[i] = false;
		}
	}
}