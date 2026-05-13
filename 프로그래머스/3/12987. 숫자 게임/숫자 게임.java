import java.util.Arrays;

class Solution {
    public static int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int result = 0;
        int aIdx = 0;
        for (int bIdx = 0; bIdx < B.length; bIdx++) {
            if (B[bIdx] > A[aIdx]) {
                aIdx++;
                result++;
            }
        }
        return result;
    }
}