class Solution {
    private static int[][] map;

    public static int solution(int m, int n, int[][] puddles) {
        map = new int[n][m];
        for (int i = 0; i < puddles.length; i++) {
            map[puddles[i][1] - 1][puddles[i][0] - 1] = -1;
        }

        return rec(0,0);
    }

    private static int rec(int x, int y) {
        if (x == map[0].length - 1 && y == map.length - 1) {
            return 1;
        }

        if (map[y][x] == -1)
            return 0;

        int dirRight, dirDown;

        if (outOfBound(x + 1, y) || map[y][x + 1] == -1) {
            dirRight = 0;
        } else if (map[y][x + 1] != 0) {
            dirRight = map[y][x + 1];
        } else {
            dirRight = rec(x + 1, y);
        }

        if (outOfBound(x, y + 1) || map[y + 1][x] == -1) {
            dirDown = 0;
        } else if (map[y + 1][x] != 0) {
            dirDown = map[y + 1][x];
        } else {
            dirDown = rec(x, y + 1);
        }

        int result = (dirRight + dirDown) % 1_000_000_007;
        map[y][x] = result;

        return result;
    }

    private static boolean outOfBound(int x, int y) {
        return 0 > x || x >= map[0].length || 0 > y || y >= map.length;
    }
}