import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N, H;
    static int[][][] farm;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        getFarm(br);
        br.close();

        if (checkTomato()) {
            System.out.println(0);
            return;
        }

        int day = bfs();

        if(checkTomato()) {
            System.out.println(day);
        }
        else {
            System.out.println(-1);
        }

    }

    private static boolean checkTomato() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (farm[i][j][k] == 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    static int[] dirX = {1,-1,0,0,0,0};
    static int[] dirY = {0,0,1,-1,0,0};
    static int[] dirZ = {0,0,0,0,1,-1};
    static int directionMax = 6;

    private static int bfs() {
        Queue<Tomato> qu = initQueue();

        int maxDay = 0;
        while (!qu.isEmpty()) {
            Tomato nowTomato = qu.poll();

            int ripeX, ripeY, ripeZ;
            for (int i = 0; i < directionMax; i++) {
                ripeX = nowTomato.x + dirX[i];
                ripeY = nowTomato.y + dirY[i];
                ripeZ = nowTomato.z + dirZ[i];


                if(isOutOfBound(ripeX, ripeY, ripeZ) || visited[ripeZ][ripeY][ripeX]) {
                    continue;
                }

                int nowTomatoRideDay = farm[nowTomato.z][nowTomato.y][nowTomato.x];
                int nextTomatoRideDay = farm[ripeZ][ripeY][ripeX];

                if (nextTomatoRideDay == -1) {
                    continue;
                }
                if (nextTomatoRideDay != 0 && nextTomatoRideDay <= nowTomatoRideDay) {
                    continue;
                }


                int nextDay = nowTomatoRideDay + 1;
                farm[ripeZ][ripeY][ripeX] = nextDay;
                visited[ripeZ][ripeY][ripeX] = true;
                maxDay = Math.max(maxDay, nextDay);
                qu.offer(new Tomato(ripeX, ripeY, ripeZ));
            }
        }


        return maxDay - 1;
    }

    private static boolean isOutOfBound(int x, int y, int z) {
        return 0 > x || x >= M ||
                0 > y || y >= N ||
                0 > z || z >= H;
    }

    private static Queue<Tomato> initQueue() {
        Queue<Tomato> qu = new LinkedList<>();

        for (int z = 0; z < H; z++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (farm[z][y][x] == 1) {
                        qu.offer(new Tomato(x,y,z));
                        visited[z][y][x] = true;
                    }
                }
            }
        }

        return qu;
    }

    static class Tomato {
        int x, y, z;

        public Tomato(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static int[][][] getFarm(BufferedReader br) throws IOException {
        farm = new int[H][N][M];
        visited = new boolean[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                farm[i][j] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
            }
        }

        return farm;
    }
}
