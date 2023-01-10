class Solution {
    int[] dp;
	public int solution(int n) {
		dp = new int[n+1];
		dp[0] = 0;
		dp[1] = 1;
		return F(n);
    }

	private int F(int n) {
		if(n == 0)
			return 0;
		
		if(dp[n] != 0)
			return dp[n];
		
		int result = (F(n-2) + F(n-1))%1234567;
		dp[n] = result;
		return result;
	}
}