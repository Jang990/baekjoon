class Solution {
    public static int solution(int n) {
        long[] dp = new long[n+1];
        int mod = 1000000007;
        dp[0] = 1;
        dp[2] = 3;
        
        //dp[n] = dp[n-2]*4 - dp[n-4]
        for (int i = 4; i < dp.length; i+=2) {
			dp[i] = (dp[i-2]*4%mod - dp[i-4]%mod + mod)%mod;
		}
        
        return (int)dp[n];
    }
}