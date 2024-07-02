import java.util.*;
class Solution {
    static int[] apeach, lion;
    static int[] result;
    static int max = Integer.MIN_VALUE;

    public static int[] solution(int n, int[] info) {
        apeach = info;
        lion = new int[11];
        shoot(0, n, 0, 0);

        if (max <= 0)
            return new int[] {-1};
        return result;
    }

    private static void shoot(int depth, int arrow, int lionScore, int apeachScore) {
        if (depth == 11) {
            if(arrow != 0)
                lion[10] = arrow;
            int diff = lionScore - apeachScore;
            if ((max == diff && hasMoreLowScore()) || max < diff) {
                max = diff;
                result = Arrays.copyOf(lion, 11);
            }
            lion[10] = 0;
            return;
        }

        int nowScore = 10 - depth;
        if (arrow > apeach[depth]) {
            int requiredArrow = apeach[depth] + 1;
            lion[depth] = requiredArrow;
            shoot(depth + 1, arrow - requiredArrow, lionScore + nowScore, apeachScore);
            lion[depth] = 0;
        }

        if(apeach[depth] == 0)
            shoot(depth + 1, arrow, lionScore, apeachScore);
        else
            shoot(depth + 1, arrow, lionScore, apeachScore + nowScore);
    }
    
    private static boolean hasMoreLowScore() {
        if(result == null)
            return true;
        int lionArrowSum = 0, resultArrowSum = 0;
        for (int i = 10; i >= 0; i--) {
            lionArrowSum += lion[i];
            resultArrowSum += result[i];
            if(lionArrowSum < resultArrowSum)
                return false;
        }

        return true;
    }
}