class Solution {
    static boolean[] visited;
	static int answer;
	
	public static int solution(int[] nums) {
        visited = new boolean[nums.length];
        answer = 0;
        
        Rec(nums, 0, 0, 0);
        
        return answer;
    }

	private static void Rec(int[] nums, int idx, int result, int selectedIdx) {
		if(idx == 3) {
			if(isPrime(result))
				answer++;
			return;
		}
		
		for (int i = selectedIdx; i < nums.length; i++) {
			if(visited[i])
				continue;
			
			visited[i] = true;
			Rec(nums, idx+1, result+nums[i], i);
			visited[i] = false;
		}
	}

	private static boolean isPrime(int result) {
		for(int i = 2; i <= (int)Math.sqrt(result); i++) {
			if(result%i == 0)
				return false;
		}
		
		return true;
	}
}