class Solution {
    public static int solution(int alp, int cop, int[][] problems) {
        int maxAl = 0;
        int maxCo = 0;

        // 알re, 코re, 알, 코, 시간
        for (int i = 0; i < problems.length; i++) {
            maxAl = Math.max(problems[i][0], maxAl);
            maxCo = Math.max(problems[i][1], maxCo);
        }

        int[][] dp = new int[maxAl+1][maxCo+1];
        if(alp > maxAl)
            alp = maxAl;
        if(cop > maxCo)
            cop = maxCo;
            
        
        for(int i = alp; i <= maxAl; i++) {
            for (int j = cop; j <= maxCo; j++) {
                setNextDpValue(dp, i, j,
                        i+1, j, 1);
                setNextDpValue(dp, i, j,
                        i, j+1, 1);
                for(int k = 0; k < problems.length; k++) {
                    if(problems[k][0] > i || problems[k][1] > j)
                        continue;

                    int alReward = problems[k][2];
                    int coReward = problems[k][3];

                    setNextDpValue(dp, i, j,
                            i+alReward, j+coReward, problems[k][4]);
                }
            }
        }

        return dp[maxAl][maxCo];
    }

    private static void setNextDpValue(int[][] dp, int nowAl, int nowCo, int nextAl, int nextCo, int time) {
        if(nextAl >= dp.length)
            nextAl = dp.length - 1;
        if(nextCo >= dp[0].length)
            nextCo = dp[0].length - 1;
        
        if(nowAl == nextAl && nowCo == nextCo)
            return;

        if (dp[nextAl][nextCo] == 0)
            dp[nextAl][nextCo] = dp[nowAl][nowCo] + time;
        else 
            dp[nextAl][nextCo] = Math.min(dp[nextAl][nextCo], dp[nowAl][nowCo]+time);
    }
}