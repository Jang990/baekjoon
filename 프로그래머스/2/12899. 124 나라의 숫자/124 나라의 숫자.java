class Solution {
    public static String solution(int n) {
        int[] num = {1, 2, 4};
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n -= 1;
            sb.insert(0, num[n % 3]);
            n /= 3;
        }
        return sb.toString();
    }
}