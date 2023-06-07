import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf).toArray();
        }
        br.close();

        createWall(0);
        System.out.println(max);
    }

    private static void createWall(int wallCnt) {
        if (wallCnt == 3) {
            int safeZoneCnt = checkSafeZone();
            max = Math.max(safeZoneCnt, max);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    createWall(wallCnt+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static int checkSafeZone() {
        Queue<Point> qu = new LinkedList<>();
        int[][] cloneMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cloneMap[i][j] = map[i][j];
                if (cloneMap[i][j] == 2) {
                    qu.add(new Point(j, i));
                }
            }
        }

        int[] dirX = {0,0,1,-1};
        int[] dirY = {1,-1,0,0};
        while (!qu.isEmpty()) {
            Point now = qu.poll();
            for (int i = 0; i < 4; i++) {
                int nowX = now.x + dirX[i];
                int nowY = now.y + dirY[i];

                if (isOutOfBoundary(nowX, nowY) || cloneMap[nowY][nowX] != 0) {
                    continue;
                }

                cloneMap[nowY][nowX] = 2;
                qu.offer(new Point(nowX, nowY));
            }
        }

        int safeZoneCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cloneMap[i][j] == 0) {
                    safeZoneCnt++;
                }
            }
        }

        return safeZoneCnt;
    }

    private static boolean isOutOfBoundary(int nowX, int nowY) {
        return 0 > nowX || nowX >= M || 0 > nowY || nowY >= N;
    }
}
