import java.util.Arrays;

class Solution {
    public static int solution(int sticker[]) {
        if(sticker.length == 1)
            return sticker[0];
        
        // dp[0] : 첫 번째를 선택하지 않은 경우
        // dp[1] : 첫 번째를 선택한 경우
        int dpLen = sticker.length + 2;
        int[][] dp = new int[2][dpLen];
        dp[1][2] = sticker[0];
        for (int i = 1; i < sticker.length; i++) {
            dp[0][i + 2] = dp[0][i] + sticker[i];
            dp[0][i + 1] = Math.max(dp[0][i], dp[0][i + 1]);

            dp[1][i + 2] = dp[1][i] + sticker[i];
            dp[1][i + 1] = Math.max(dp[1][i], dp[1][i + 1]);
        }

        // dp[][len] -> 마지막 스티커 선택 -> dp[0]은 마지막 스티커를 선택할 수 있다.
        // dp[][len - 1] -> 마지막 스티커 선택 x -> dp[1]은 마지막 스티커를 선택할 수 없다.
        return Math.max(dp[0][dpLen - 1], dp[1][dpLen - 2]);
    }
}