import java.util.Arrays;
class Solution {
    public int solution(int k, int[] tangerine) {
        int max = Arrays.stream(tangerine).max().getAsInt();
        int[] arr = new int[max + 1];
        for (int i : tangerine) {
            arr[i]++;
        }

        int result = 0;
        Arrays.sort(arr);
        for (int i = max; i >= 0; i--) {
            k -= arr[i];
            result++;

            if(k <= 0)
                break;
        }
        return result;
    }
}