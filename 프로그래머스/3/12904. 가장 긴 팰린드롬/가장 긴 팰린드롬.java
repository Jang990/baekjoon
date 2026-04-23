class Solution
{
    public static int solution(String s) {
        char[] arr = s.toCharArray();

        int result = 1;
        for (int left = 0; left < arr.length; left++) {
            for (int right = left + 1; right < arr.length; right++) {
                if(isOk(arr, left, right))
                    result = Math.max(result, right - left + 1);
            }
        }
        return result;
    }

    private static boolean isOk(char[] arr, int left, int right) {
        while (left <= right) {
            if(arr[left] != arr[right])
                return false;
            left++;
            right--;
        }
        return true;
    }
}