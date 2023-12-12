import java.util.Arrays;

class Solution {
    public static int solution(int[] stones, int k) {
        int left = 0, right = Arrays.stream(stones).max().getAsInt() + 1;
        while (left < right) {
            int mid = (left + right) / 2;

            if(left == mid)
                right--;
            else if (isOk(stones, mid, k))
                left = mid;
            else
                right = mid;
        }

        return left;
    }

    // 3 , 13
    private static boolean isOk(int[] stones, int mid, int k) {
        int cnt = 0;
        for (int i = 0; i < stones.length; i++) {
            if(stones[i] >= mid)
                cnt = 0;
            else
                cnt++;

            if(cnt >= k)
                return false;
        }
        return true;
    }
}