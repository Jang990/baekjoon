class Solution {
    static int min = Integer.MAX_VALUE;
    public static int solution(int storey) {
        rec(storey, 0);
        return min;
    }

    private static void rec(int storey, int stone) {
        if (storey == 0) {
            min = Math.min(min, stone);
            return;
        }

        rec(storey / 10, stone + storey % 10);
        if(storey % 10 >= 5)
            rec(storey / 10 + 1, stone + 10 - storey % 10);
    }
}