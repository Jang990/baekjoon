class Solution {
    public static int solution(int storey) {
        return DFS(storey);
    }

    private static int DFS(int storey) {
        if(storey <= 1) {
            return storey;
        }

        int div = storey/10;
        int mod = storey%10;

        int down = mod + DFS(div);
        int up = 10-mod + DFS(div+1);

        return Math.min(down, up);
    }
}