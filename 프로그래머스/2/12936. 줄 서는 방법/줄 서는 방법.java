import java.util.ArrayList;
import java.util.List;

class Solution {
    public static int[] solution(int n, long k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        k--;
        int[] result = new int[n];
        long now = factorial(n);
        for (int i = 0; i < n; i++) {
            now = now / (n-i);
            int idx = (int) (k / now);
            result[i] = list.get(idx);
            list.remove(idx);
            k %= now;
        }

        return result;
    }

    static long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}