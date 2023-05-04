import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
class Solution {
    static String[][] map;
    static int[][] visited;
    public static int solution(String[] board) {
        Point rPoint = null;
        map = new String[board.length][board[0].length()];
        visited = new int[board.length][board[0].length()];

        for (int i = 0; i < map.length; i++) {
            map[i] = board[i].split("");

            if(rPoint != null)
                continue;

            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].equals("R")) {
                    rPoint = new Point(j, i);
                    break;
                }
            }
        }

        return BFS(rPoint);
    }

    private static int BFS(Point rPoint) {
        Queue<Point> qu = new LinkedList<>();
        visited[rPoint.y][rPoint.x] = 1;
        qu.offer(rPoint);

        int[] dirX = {0,0,1,-1};
        int[] dirY = {1,-1,0,0};
        while (!qu.isEmpty()) {
            Point now = qu.poll();
            for (int i = 0; i < dirX.length; i++) {
                Point next = move(now, dirX[i], dirY[i]);
                if (next.equals(now) || visited[next.y][next.x] != 0) {
                    continue;
                }

                if (map[next.y][next.x].equals("G")) {
                    return visited[now.y][now.x];
                }
                visited[next.y][next.x] = visited[now.y][now.x]+1;
                qu.offer(next);
            }
        }

        return -1;
    }

    private static Point move(Point now, int moveX, int moveY) {
        int nowX = now.x;
        int nowY = now.y;
        while(checkBoundary(nowX+moveX,nowY+moveY) && !map[nowY+moveY][nowX+moveX].equals("D")) {
            nowX += moveX;
            nowY += moveY;
        }
        return new Point(nowX, nowY);
    }

    private static boolean checkBoundary(int nowX, int nowY) {
        return 0 <= nowX && nowX < map[0].length && 0 <= nowY && nowY < map.length;
    }
}