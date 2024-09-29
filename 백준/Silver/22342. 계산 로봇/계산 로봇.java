import java.io.*;
import java.util.Arrays;

public class Main {

    private static int[][] weigths;
    private static int[][] stored;
    private static int[][] outs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]), M = Integer.parseInt(line[1]);
        weigths = new int[N][M];
        stored = new int[N][M];
        outs = new int[N][M];
        for (int i = 0; i < N; i++) {
            weigths[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        // 초기설정 : 가장 왼쪽 열 [out = 0(입력값) + 가중치] 설정
        for (int y = 0; y < N; y++) {
            outs[y][0] = weigths[y][0];
        }

        for (int x = 1; x < M; x++) {
            for (int y = 0; y < N; y++) {
                outs[y][x] = findStoredValue(x, y) + weigths[y][x];
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result = Math.max(outs[i][j] - weigths[i][j], result);
            }
        }
        System.out.println(result);
    }

    private static int findStoredValue(int x, int y) {
        return stored[y][x] = findMaxIn(x, y);
    }

    private static int findMaxIn(int x, int y) {
        int currentX = x - 1;
        if(currentX < 0)
            return 0;

        int result = 0;
        int[] dirY = {-1, 0, 1};

        for (int i = 0; i < dirY.length; i++) {
            int currentY = y + dirY[i];
            if(0 > currentY || currentY >= outs.length)
                continue;

            if(outs[currentY][currentX] == 0)
                result = Math.max(findMaxIn(currentX, currentY), result);
            else
                result = Math.max(outs[currentY][currentX], result);
        }

        return result;
    }
}

