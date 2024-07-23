import java.awt.*;
import java.util.*;

class Solution {
    static int[][] visited;
    public static int solution(String[] board) {
        visited = new int[board.length][board[0].length()];
        // R 로봇의 처음 위치, D 장애물의 위치, G 목표지점
        Point start = null, end = null;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if(board[i].charAt(j) == 'R')
                    start = new Point(j, i);
                else if(board[i].charAt(j) == 'G')
                    end = new Point(j, i);
                else if(board[i].charAt(j) == 'D')
                    visited[i][j] = -1;
            }
        }

        return BFS(start, end);
    }

    private static int BFS(Point start, Point end) {
        Queue<Point> qu = new LinkedList<>();
        visited[start.y][start.x] = 1;
        qu.offer(start);

        int[] dirX = {0, 0, 1, -1},
                dirY = {1, -1, 0, 0};
        while (!qu.isEmpty()) {
            Point now = qu.poll();
            for (int i = 0; i < 4; i++) {
                Point next = move(now, dirX[i], dirY[i]);
                int nowStep = visited[now.y][now.x] + 1;
                if(visited[next.y][next.x] != 0 && visited[next.y][next.x] <= nowStep) continue;
                if(next.equals(end))
                    return nowStep - 1;
                qu.offer(next);
                visited[next.y][next.x] = nowStep;
            }
        }
        return -1;
    }

    private static Point move(Point now, int dirX, int dirY) {
        int nowX = now.x, nowY = now.y;
        while (true) {
            int nextX = nowX + dirX;
            int nextY = nowY + dirY;
            if(isOutOfBound(nextX, nextY) || visited[nextY][nextX] == -1)
                break;
            nowX = nextX;
            nowY = nextY;
        }
        return new Point(nowX, nowY);
    }

    private static boolean isOutOfBound(int x, int y) {
        return 0 > x || x >= visited[0].length || 0 > y || y >= visited.length;
    }
}