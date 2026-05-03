import java.util.ArrayList;

class Solution {
    public static int[] solution(int n, long k) {
        int[] result = new int[n];

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            list.add(i);

        k--;
        for (int i = 0; i < n; i++) {
            long way = factorial(n - 1 - i);
            int selected = Math.toIntExact(k / way);

            result[i] = list.get(selected);
            list.remove(selected);

            k -= (way * selected);
        }

        return result;
    }

    private static long factorial(int num) {
        long result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}