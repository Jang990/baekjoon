import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static String[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.valueOf(st.nextToken());
        int C = Integer.valueOf(st.nextToken());
        map = new String[R][C];
        String[][] result = new String[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().split("");
            result[i] = Arrays.copyOf(map[i], C);
        }

        br.close();


        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j].equals(".")) {
                    continue;
                }

                if (isSubmerged(j, i)) {
                    result[i][j] = ".";
                }
            }
        }

        int startX = C-1, endX = 0, startY = R-1, endY = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (result[i][j].equals(".")) {
                    continue;
                }

                if (startX > j) {
                    startX = j;
                }
                if (endX < j) {
                    endX = j;
                }
                if (startY > i) {
                    startY = i;
                }
                if (endY < i) {
                    endY = i;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = startY; i <= endY; i++) {
            for (int j = startX; j <= endX; j++) {
                sb.append(result[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static boolean isSubmerged(int x, int y) {
        int seaCnt = 0;
        int[] dirX = {0, 0, -1, 1};
        int[] dirY = {-1, 1, 0, 0};
        int nowX, nowY;
        for (int i = 0; i < 4; i++) {
            nowX = x + dirX[i];
            nowY = y + dirY[i];
            if (outOfBound(nowX, nowY) || map[nowY][nowX].equals(".")) {
                seaCnt++;
            }
        }

        if (seaCnt >= 3) {
            return true;
        }

        return false;
    }

    private static boolean outOfBound(int nowX, int nowY) {
        return 0 > nowX || nowX >= map[0].length || 0 > nowY || nowY >= map.length;
    }


}
