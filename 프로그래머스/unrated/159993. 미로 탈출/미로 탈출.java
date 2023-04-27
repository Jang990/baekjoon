import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
class Solution {
    static String[][] map;
    public static int solution(String[] maps) {
        int answer = 0;
        map = new String[maps.length][maps[0].split("").length];

        int startX=0, startY=0;
        int leverX=0, leverY=0;
        for (int i = 0; i < maps.length; i++) {
            String[] line = maps[i].split("");
            for (int j = 0; j < line.length; j++) {
                map[i][j] = line[j];
                if(map[i][j].equals("S")) {
                    startX = j;
                    startY = i;
                }
                if (map[i][j].equals("L")) {
                    leverX = j;
                    leverY = i;
                }
            }
        }
        Point start = new Point(startX, startY);
        int startToLever = BFS(start, "L");
        if (startToLever == 0) {
            return -1;
        }

        Point lever = new Point(leverX, leverY);
        int leverToEnd = BFS(lever, "E");
        if (leverToEnd == 0) {
            return -1;
        }


        return startToLever+leverToEnd;
    }

    private static int BFS(Point start, String search) {
        Queue<Point> qu = new LinkedList<>();
        int[][] step = new int[map.length][map[0].length];
        qu.offer(start);
        step[start.y][start.x] = 1;

        int[] dirX = {0,0,1,-1};
        int[] dirY = {1,-1,0,0};
        while (!qu.isEmpty()) {
            Point now = qu.poll();
            int nextX, nextY;
            for (int i = 0; i < dirX.length; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];

                if(checkBoundary(nextX, nextY) || map[nextY][nextX].equals("X") || step[nextY][nextX] != 0) {
                    continue;
                }

                step[nextY][nextX] = step[now.y][now.x]+1;
                qu.offer(new Point(nextX, nextY));
                if (map[nextY][nextX].equals(search)) {
                    return step[nextY][nextX]-1;
                }
            }

        }

        return 0;
    }

    private static boolean checkBoundary(int nowX, int nowY) {
        return nowX < 0 || map[0].length <= nowX || nowY < 0 || map.length <= nowY;
    }

}