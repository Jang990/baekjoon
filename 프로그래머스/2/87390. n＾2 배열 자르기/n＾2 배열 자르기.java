class Solution {
    public static int[] solution(int n, long left, long right) {
        int[] result = new int[(int) (right - left + 1)];
        for (int i = 0; i < result.length; i++) {
            long now = left + i;
            result[i] = convert(n, now);
        }
        return result;
    }

    private static int convert(int n, long now) {
        int share = (int) (now / n);
        int rest = (int) (now % n);
        if(rest < share)
            return share + 1;
        return rest + 1;
    }
}