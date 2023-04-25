import java.util.Arrays;

class Solution {
        static int minFatigue = Integer.MAX_VALUE;
    static int maxDig;
    static int[] pick;
    static String[] mineral;
    public static int solution(int[] picks, String[] minerals) {
        maxDig = getMaxDigCount(picks, minerals);
        pick = picks;
        mineral = minerals;
        DFS(0, 0);

        return minFatigue;
    }

    private static void DFS(int depth, int sumFatigue) {
        if (depth * 5 >= maxDig) {
            minFatigue = Math.min(minFatigue, sumFatigue);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (pick[i] == 0) {
                continue;
            }

            pick[i]--;
            DFS(depth + 1, sumFatigue + sumFatigue(depth, i));
            pick[i]++;
        }
    }

    private static int sumFatigue(int depth, int select) {
        int sum = 0;

        int nowIdx = depth == 0 ? 0 : depth*5;
        for (int i = 0; i < 5; i++) {
            if(nowIdx >= mineral.length)
                continue;
            int before = sum;
            sum += calcPickFatigue(select, mineral[nowIdx]);
            nowIdx++;
        }

        return sum;
    }

    private static int calcPickFatigue(int select, String s) {
        if (s.equals("diamond")) {
            return 25 / (int) Math.pow(5, 2 - select);
        }
        if (s.equals("iron") && select == 2) {
            return 5;
        }

        return 1;
    }

    private static int getMaxDigCount(int[] picks, String[] minerals) {
        int pickCnt = sumPicksCount(picks) * 5;
        if (minerals.length <= pickCnt) {
            return minerals.length;
        }
        else {
            return pickCnt;
        }
    }

    private static int sumPicksCount(int[] picks) {
        int sum = 0;
        for (int pick : picks) {
            sum += pick;
        }
        return sum;
    }
}