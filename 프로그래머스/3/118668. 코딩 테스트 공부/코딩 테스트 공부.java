class Solution {
    static int[][] dp;
    static int maxAl = 0, maxCo = 0;

    // 알re, 코re, 알, 코, 시간
    public static int solution(int alp, int cop, int[][] problems) {
        int[] problemSelfAl = {0, 0, 1, 0, 1};
        int[] problemSelfCo = {0, 0, 0, 1, 1};

        for (int[] problem : problems) {
            maxAl = Math.max(maxAl, problem[0]);
            maxCo = Math.max(maxCo, problem[1]);
        }

        if(alp > maxAl)
            alp = maxAl;
        if(cop > maxCo)
            cop = maxCo;

        dp = new int[maxAl + 1][maxCo + 1];
        for (int i = alp; i <= maxAl; i++) {
            for (int j = cop; j <= maxCo; j++) {
                next(i, j, problemSelfAl);
                if(dp[5][1] == 1)
                    System.out.println(i + "," + j);
                next(i, j, problemSelfCo);
                for (int[] problem : problems) {
                    next(i, j, problem);
                }
            }
        }
        return dp[maxAl][maxCo];
    }

    // 알re, 코re, 알, 코, 시간
    private static void next(int al, int co, int[] problem) {
        if(problem[0] > al || problem[1] > co)
            return;
        int nextAl = Math.min(maxAl, al + problem[2]);
        int nextCo = Math.min(maxCo, co + problem[3]);
        int nextTime = dp[al][co] + problem[4];
        if(al == nextAl && co == nextCo)
            return;
        if(dp[nextAl][nextCo] != 0 && dp[nextAl][nextCo] <= nextTime)
            return;
        dp[nextAl][nextCo] = nextTime;
    }
}