import java.awt.*;
// 움직이는 순서 중요하다.
// 같은 턴에 red가 먼저 움직이는 경우.
// 같은 턴에서 blue가 먼저 움직이는 경우.
// 두 경우는 다른 결과가 나올 수 있다.
class Solution {
    static boolean[][] redVisited;
    static boolean[][] blueVisited;
    static Point redEnd, blueEnd;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;

    enum Turn {
        RED, BLUE
    }

    public static int solution(int[][] maze) {
        map = maze;
        redVisited = new boolean[map.length][map[0].length];
        blueVisited = new boolean[map.length][map[0].length];
        Point red = null, blue = null;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1) {
                    redVisited[i][j] = true;
                    red = new Point(j, i);
                } else if (map[i][j] == 2) {
                    blueVisited[i][j] = true;
                    blue = new Point(j, i);
                } else if (map[i][j] == 3) {
                    redEnd = new Point(j, i);
                } else if (map[i][j] == 4) {
                    blueEnd = new Point(j, i);
                }
            }
        }

        redVisited = new boolean[map.length][map[0].length];
        blueVisited = new boolean[map.length][map[0].length];
        rec(0, red, blue, Turn.RED);
        rec(0, red, blue, Turn.BLUE);

        if(answer == Integer.MAX_VALUE)
            return 0;
        return answer;
    }

    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};
    public static void rec(int depth, Point red, Point blue, Turn turn) {
        if (depth % 2 == 0 && isRedEnd(red) && isBlueEnd(blue)) {
            answer = Math.min(answer, depth / 2);
            return;
        }

        if (turn.equals(Turn.RED)) {
            redMove(depth, red, blue);
        } else {
            blueMove(depth, red, blue);
        }
    }

    private static void blueMove(int depth, Point red, Point blue) {
        if (isBlueEnd(blue)) {
            rec(depth+1, red, blue, Turn.RED);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = blue.x + dirX[i];
            int nextY = blue.y + dirY[i];
            Point next = new Point(nextX, nextY);
            if(outOfBound(nextX, nextY) || blueVisited[nextY][nextX] || map[nextY][nextX] == 5 || next.equals(red))
                continue;

            blueVisited[nextY][nextX] = true;
            rec(depth + 1, red, next, Turn.RED);
            blueVisited[nextY][nextX] = false;
        }
    }

    private static void redMove(int depth, Point red, Point blue) {
        if (isRedEnd(red)) {
            rec(depth+1, red, blue, Turn.BLUE);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = red.x + dirX[i];
            int nextY = red.y + dirY[i];
            Point next = new Point(nextX, nextY);
            if(outOfBound(nextX, nextY) || redVisited[nextY][nextX] || map[nextY][nextX] == 5 || next.equals(blue))
                continue;

            redVisited[nextY][nextX] = true;
            rec(depth + 1, next, blue, Turn.BLUE);
            redVisited[nextY][nextX] = false;
        }
    }

    private static boolean isBlueEnd(Point blue) {
        return blueEnd.equals(blue);
    }

    private static boolean isRedEnd(Point red) {
        return redEnd.equals(red);
    }

    private static boolean outOfBound(int x, int y) {
        return 0 > x || x >= map[0].length || 0 > y || y >= map.length;
    }
}