class Solution {
    public static int solution(int n) {
        int result = n + 1;
        while (countOne(n) != countOne(result)) {
            result++;
        }
        return result;
    }

    private static int countOne(int num) {
        int result = 0;
        char[] arr = Integer.toBinaryString(num).toCharArray();

        for (char c : arr) {
            if(c == '1')
                result++;
        }

        return result;
    }
}