import java.util.*;
class Solution {
    static int[] lion;
    static int[] apeach;
    static int[] result;
    static int diff = Integer.MIN_VALUE;
    public static int[] solution(int n, int[] info) {
        apeach = info;
        lion = new int[11];
        rec(-1, n);
        if (diff <= 0) {
            return new int[] {-1};
        }
        return result;
    }

    private static void rec(int depth, int arrow) {
        if ((depth != 10 && !isScoreable(depth + 1, arrow))) {
            lion[10] = arrow;
            rec(10, 0);
            lion[10] = 0;
            return;
        }

        if (arrow == 0) {
            calc();
            return;
        }

        for (int i = depth + 1; i < 10; i++) {
            if(apeach[i] >= arrow)
                continue;

            lion[i] = apeach[i]+1;
            rec(i, arrow - (apeach[i]+1));
            lion[i] = 0;
        }
    }

    private static boolean isScoreable(int now, int arrow) {
        for (int i = now; i < 10; i++) {
            if(apeach[i] < arrow)
                return true;
        }
        return false;
    }

    private static void calc() {
        int apeachScore = 0;
        int lionScore = 0;
        for (int i = 0; i < 10; i++) {
            if(apeach[i] == 0 && lion[i] == 0)
                continue;

            if(apeach[i] < lion[i])
                lionScore += (10 - i);
            else
                apeachScore += (10 - i);
        }

        int nowDiff = lionScore - apeachScore;
        if (nowDiff > diff || (nowDiff == diff &&hasMoreLowScore(lion, result))) {
            diff = nowDiff;
            result = Arrays.copyOf(lion, 11);
        }
    }
    
    private static boolean hasMoreLowScore(int[] lion, int[] result) {
        if(result == null)
            return true;
        
        for (int i = 10; i >= 0; i--) {
            if(lion[i] == result[i])
                continue;
            if(lion[i] > result[i])
                return true;
            else
                return false;
        }
        return false;
    }
}