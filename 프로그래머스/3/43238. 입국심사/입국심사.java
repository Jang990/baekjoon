import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long start = 0, end = (long) n * times[0];
        while(start < end) {
            long mid = (start + end) / 2;
            long result = getResult(mid, times);
            if(result >= n)
                end = mid;
            else
                start = mid + 1;
        }
        return start;
    }
    
    public long getResult(long time, int[] times) {
        long sum = 0;
        for(int i = 0; i < times.length; i++) {
            sum += time / times[i];
        }
        return sum;
    }
}