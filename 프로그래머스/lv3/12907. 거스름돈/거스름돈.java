class Solution {
    public int solution(int n, int[] money) {
        int[][] dp = new int[money.length][n + 1];
        for (int i = 0; i < money.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < money.length; i++) {
            for (int j = 1; j <= n; j++) {
                int n1 = (j - money[i] >= 0) ? dp[i][j - money[i]] : 0;
                int n2 = (i - 1 >= 0) ? dp[i-1][j] : 0;

                dp[i][j] = n1 + n2;
            }
        }

        return dp[money.length-1][n];
    }
}