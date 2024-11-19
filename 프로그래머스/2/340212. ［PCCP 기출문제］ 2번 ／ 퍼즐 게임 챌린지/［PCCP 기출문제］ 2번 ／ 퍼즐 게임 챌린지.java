class Solution {
    public static int solution(int[] diffs, int[] times, long limit) {
        int left = 1, right = 100_000;
        while (left < right) {
            int mid = (left + right) / 2;
            long time = solve(diffs, times, mid);
            if(time <= limit)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

    private static long solve(int[] diffs, int[] times, int level) {
        long result = 0;
        int prevTime = 0;
        for (int i = 0; i < diffs.length; i++) {
            result += solve(diffs[i], times[i], prevTime, level);
            prevTime = times[i];
        }

        return result;
    }

    private static int solve(int diff, int time, int prevTime, int level) {
        if(diff <= level)
            return time;
        return (prevTime + time) * (diff - level) + time;
    }
}