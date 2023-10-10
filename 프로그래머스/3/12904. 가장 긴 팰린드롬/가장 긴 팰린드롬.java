class Solution
{
    public static int solution(String s) {
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length() - i; j++) {
                if (isPalindrome(s, i, i + j + 1)) {
                    answer = Math.max(j + 1, answer);
                }
            }
        }

        return answer;
    }

    static boolean isPalindrome(String str, int start, int end) {
        int length = end - start;
        for (int i = 0; i < length / 2; i++) {
            if (str.charAt(start+i) != str.charAt(end - i - 1)) {
                return false;
            }
        }
        return true;
    }
}