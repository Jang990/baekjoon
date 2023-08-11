class Solution {
    public long solution(int[] sequence) {
        long answer = Math.abs(sequence[0]);

        long purse = 1;
        long[][] dp = new long[2][sequence.length];
        long maxSeq = 0;

        dp[0][0] = sequence[0];
        dp[1][0] = sequence[0]*-1;
        if (dp[0][0] < 0) {
            dp[0][0] = 0;
        }
        if (dp[1][0] < 0) {
            dp[1][0] = 0;
        }
        purse *= -1;

        for (int i = 1; i < sequence.length; i++) {
            maxSeq = Math.max(maxSeq, Math.abs(sequence[i]));
            long dp1 = (sequence[i] * purse) + dp[0][i - 1];
            long dp2 = (sequence[i] * purse * -1) + dp[1][i - 1];
            if (dp1 > 0) {
                dp[0][i] = dp1;
            }
            if (dp2 > 0) {
                dp[1][i] = dp2;
            }

            answer = Math.max(dp[0][i],answer);
            answer = Math.max(dp[1][i],answer);
            purse *= -1;
        }

        if (answer == 0) {
            return maxSeq;
        }

        return answer;
    }
}