class Solution {
    static int[] visited = new int[1_000_001];
    public static int solution(int x, int y, int n) {
        if(x == y) return 0;
        convert(x, y, n);

        for (int i = x; i <= y; i++) {
            if(visited[i] == 0) continue;
            convert(i, y, n);
        }

        if(visited[y] == 0) return -1;
        else return visited[y];
    }

    private static void convert(int now, int goal, int n) {
        int nextConvertedCnt = visited[now] + 1;
        next(now * 2, goal, nextConvertedCnt);
        next(now * 3, goal, nextConvertedCnt);
        next(now + n, goal, nextConvertedCnt);
    }

    private static void next(int nextNum, int goal, int convertedCnt) {
        if (nextNum <= goal && (visited[nextNum] == 0 || visited[nextNum] > convertedCnt))
            visited[nextNum] = convertedCnt;
    }
}