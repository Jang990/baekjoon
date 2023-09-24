class Solution {
    static int[][] dp;

    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;
        dp = new int[board.length+1][board[0].length+1];

        for (int[] arr : skill) {
            int sign = arr[0] == 1 ? -1 : 1;
            int y1 = arr[1];
            int x1 = arr[2];
            int y2 = arr[3]+1;
            int x2 = arr[4]+1;
            int num = arr[5] * sign;

            dp[y1][x1] += num;
            dp[y1][x2] += (num * -1);
            dp[y2][x1] += (num * -1);
            dp[y2][x2] += num;
        }

        sum();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] + dp[i][j] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private static void sum() {
        for (int i = 0; i < dp.length; i++) {
            int sum = 0;
            for (int j = 0; j < dp[0].length; j++) {
                sum += dp[i][j];
                dp[i][j] = sum;
            }
        }

        for (int i = 0; i < dp[0].length; i++) {
            int sum = 0;
            for (int j = 0; j < dp.length; j++) {
                sum += dp[j][i];
                dp[j][i] = sum;
            }
        }
    }
}