class Solution {
    public static int solution(String s) {
        int answer = s.length();

        for (int i = 1; i <= s.length(); i++) {
            int result = compress(s, i);
            answer = Math.min(result, answer);
        }

        return answer;
    }

    private static int compress(String target, int compressionLength) {
        int result = target.length();
        String prevStr = "";
        int cnt = 1;
        for (int i = 0; i < target.length(); i += compressionLength) {
            int endIndex = i + compressionLength;
            if (endIndex > target.length()) {
                endIndex = target.length();
            }

            String nowStr = target.substring(i, endIndex);
            if(prevStr.equals(nowStr)) {
                cnt++;
            }
            else {
                cnt = 1;
            }

            if (cnt == 2 || cnt == 10 || cnt == 100 || cnt == 1000) {
                result++;
            }

            if (cnt >= 2) {
                result -= compressionLength;
            }
            prevStr = nowStr;
        }

        return result;
    }
}