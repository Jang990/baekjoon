class Solution {
    static int[][] graph;
    static boolean[][] visited;
    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0,0, 1, -1};
    static int[] dir = {0,0,1,1};
    static int result = Integer.MAX_VALUE;
    static int possible = 400;

    public static int solution(int[][] board) {
        graph = board;
        visited = new boolean[graph.length][graph[0].length];
        visited[0][0] = true;
        rec(0, 0, 0);

        visited = new boolean[graph.length][graph[0].length];
        visited[0][0] = true;
        rec(0, 0, 1);
        return result;
    }

    private static void rec(int x, int y, int beforeDir) {
        if (y == graph.length-1 && x == graph[0].length-1) {
            result = Math.min(graph[y][x], result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];
            if(outOfBound(nextX, nextY) || graph[nextY][nextX] == 1 || visited[nextY][nextX])
                continue;

            int cost = 100;
            if(dir[i] != beforeDir)
                cost += 500;

            if(graph[nextY][nextX] != 0 && graph[nextY][nextX] + possible < graph[y][x] + cost)
                continue;

            visited[nextY][nextX] = true;
            graph[nextY][nextX] = graph[y][x] + cost;
            rec(nextX, nextY,  dir[i]);
            visited[nextY][nextX] = false;
        }
    }

    private static boolean outOfBound(int x, int y) {
        return 0 > x || x >= graph[0].length || 0 > y || y >= graph.length;
    }
}