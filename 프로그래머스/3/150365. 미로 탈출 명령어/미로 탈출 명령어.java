import java.util.*;
class Solution {
    static String result = "impossible";
    static char[] logs;
    static int endX, endY;
    static int width, height;
    static int maxStep;
    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        // n * m
        // x,y -> r,c
        // k번 이동
        height = n;
        width = m;
        endX = c;
        endY = r;
        maxStep = k;
        logs = new char[maxStep];

        if(isUnavailable(0, y, x))
            return result;

        // d-> l -> r -> u
        rec(0, y, x, logs);
        return result;
    }

    static char[] dirC = {'d', 'l', 'r', 'u'};
    static int[] dirX = {0, -1, 1, 0};
    static int[] dirY = {1, 0, 0, -1};

    static void rec(int depth, int nowX, int nowY, char[] log) {
        if(depth == maxStep && nowX == endX && nowY == endY) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < log.length; i++) {
                sb.append(log[i]);
            }
            // System.out.println(sb.toString().compareTo(result));
            result = sb.toString();

            return;
        }

        if(depth == maxStep 
                || isUnavailable(depth, nowX, nowY) 
                || !result.equals("impossible"))
            return;

        for(int i = 0 ; i < 4; i++) {
            int nextX = nowX + dirX[i];
            int nextY = nowY + dirY[i];
            if(isOutOfBound(nextX, nextY))
                continue;

            logs[depth] = dirC[i];
            rec(depth+1, nextX, nextY, logs);
        }
    }

    private static boolean isOutOfBound(int nextX, int nextY) {
        return 1 > nextX || nextX > width || 1 > nextY || nextY > height;
    }

    static boolean isUnavailable(int depth, int nowX, int nowY) {
        int distance = Math.abs(endX - nowX) + Math.abs(endY - nowY);
        int remainsStep = maxStep - depth;
        return remainsStep < distance || (remainsStep - distance) % 2 == 1;
    }
}