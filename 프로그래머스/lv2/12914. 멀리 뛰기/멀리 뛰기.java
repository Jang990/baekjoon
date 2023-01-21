class Solution {
    public static long solution(int n) {
        if(n == 1)
			return 1;
		
        long[] dp = new long[n+1];
        dp[1] = 1;
        dp[2] = 2;
        
        for (int i = 3; i < dp.length; i++) {
			dp[i] = (dp[i-1] + dp[i-2])%1234567;
		}
        
        return dp[n];
    }
	/*
	1: 1		1
	2: 11, 2		2
	3: 111, 12, 21		3
	4: 1111, 211,121,112, 22 5
	5: 11111, 1112,1121,1211,2111,221,212,122		8 
	 */
}