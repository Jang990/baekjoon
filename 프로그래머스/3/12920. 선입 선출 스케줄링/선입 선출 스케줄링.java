class Solution {
    public static int solution(int n, int[] cores) {
        long jobFinishedTime = findFinishedTime(n, cores);
        long prevFinishedJob = getFinishedJob(jobFinishedTime - 1, cores);
        return findWorkedCoreId(jobFinishedTime, n - prevFinishedJob, cores);
    }

    private static int findWorkedCoreId(long finishedTime, long order, int[] cores) {
        int workedCoreCnt = 0;
        for (int coreIdx = 0; coreIdx < cores.length; coreIdx++) {
            if(finishedTime % cores[coreIdx] > 0)
                continue;
            workedCoreCnt++;
            if(workedCoreCnt == order)
                return coreIdx + 1;
        }
        throw new IllegalArgumentException();
    }

    private static long findFinishedTime(int jobCnt, int[] cores) {
        long left = 0, right = 1_000_000_000;
        while (left < right) {
            long currentTime = (left + right) / 2;
            long finishedJobCnt = getFinishedJob(currentTime, cores);
            if(jobCnt > finishedJobCnt)
                left = currentTime + 1;
            else
                right = currentTime;
        }
        return left;
    }

    private static long getFinishedJob(long time, int[] cores) {
        long result = cores.length;
        for (int core : cores) {
            result += (time / core);
        }
        return result;
    }
}