import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public static long solution(int n, int[] works) {
        int left = 0, right = 50_000;
        while(left < right){
            int mid = left + (right - left) / 2;
            int cnt = count(works, mid);
            if(cnt < n)
                right = mid;
            else
                left = mid + 1;
        }

        int time = left;
        int remain = n - count(works, time);

        if(time == 0)
            return 0;

        long result = 0;
        for (int i = 0; i < works.length; i++) {
            if (works[i] < time)
                result += (long) works[i] * works[i];
            else if (works[i] >= time && remain == 0)
                result += (long) (time) * (time);
            else if (works[i] >= time && remain != 0) {
                result += (long) (time - 1) * (time - 1);
                remain--;
            }
        }
        return result;
    }

    private static int count(int[] works, int time) {
        int sum = 0;
        for (int i = 0; i < works.length; i++) {
            if(works[i] > time)
                sum += (works[i] - time);
        }
        return sum;
    }
}