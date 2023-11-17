class Solution {
    int[][] dp;

    public int solution(int[][] board, int[][] skill) {
        dp = new int[board.length+1][board[0].length+1];
        initDp(skill);
        sumDp();
        int result = countDestroyedBuilding(board);
        return result;
    }
    
    void initDp(int[][] skill) {
        for(int i = 0; i< skill.length; i++) {
            boolean isAttack = (skill[i][0] == 1);
            int x1 = skill[i][1];
            int y1 = skill[i][2];
            int x2 = skill[i][3] + 1;
            int y2 = skill[i][4] + 1;
            int degree = skill[i][5];
            
            if(isAttack)
                degree *= -1;
            
            dp[x1][y1] += degree;
            dp[x1][y2] += degree * -1;
            dp[x2][y1] += degree * -1;
            dp[x2][y2] += degree;
        }
        
    }
    
    void sumDp() {
        int sum;
        for(int i = 0; i < dp.length; i++) {
            sum = dp[i][0];
            for(int j = 1; j < dp[0].length; j++) {
                dp[i][j] += dp[i][j-1];
            }
        }
        
        for(int i = 0; i < dp[0].length; i++) {
            for(int j = 1; j < dp.length; j++) {
                dp[j][i] += dp[j-1][i];
            }
        }
    }
    
    int countDestroyedBuilding(int[][] board) {
        int count = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                board[i][j] += dp[i][j];
                if(board[i][j] > 0) 
                    count++;
            }
        }
        
        return count;
    }
}