import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());
        int B = Integer.valueOf(st.nextToken());
        int[][] map = new int[N][M];

        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf).toArray();

            for (int j = 0; j < M; j++) {
                maxHeight = Math.max(map[i][j], maxHeight);
            }
        }
        br.close();

        int result = Integer.MAX_VALUE;
        int height = 0;
        for (int i = 0; i <= maxHeight; i++) {
            int time = calcTime(map, i, B);
            if (result >= time) {
                result = time;
                height = i;
            }
        }

        System.out.println(result + " " + height);
    }

    private static int calcTime(int[][] map, int height, int inventory) {
        int time = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int nowHeight = map[i][j];
                if (nowHeight == height) {
                    continue;
                }
                else if(nowHeight < height) {
                    // 작을 때
                    int stackedBlock = height - nowHeight;
                    time += stackedBlock;
                    inventory -= stackedBlock;
                }
                else {
                    // 클 때
                    int diggingBlock = nowHeight - height;
                    time += (diggingBlock * 2);
                    inventory += diggingBlock;
                }
            }
        }

        if (inventory < 0) {
            return Integer.MAX_VALUE;
        }

        return time;
    }
}
