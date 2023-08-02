import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static int[][][] visited;
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());
        graph = new int[N][M];
        visited = new int[2][N][M];
        for (int i = 0; i < N; i++) {
            graph[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::valueOf).toArray();
            Arrays.fill(visited[0][i], Integer.MAX_VALUE);
            Arrays.fill(visited[1][i], Integer.MAX_VALUE);
        }
        br.close();

        bfs();

        int result = Math.min(visited[0][N-1][M-1], visited[1][N-1][M-1]);
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else {
            System.out.println(result);
        }

    }

    private static void bfs() {
        Queue<Point> qu = new LinkedList<>();
        qu.offer(new Point(0, 0, false));
        visited[0][0][0] = 1;

        int[] dirX = {0, 0, -1, 1};
        int[] dirY = {-1, 1, 0, 0};
        while (!qu.isEmpty()) {
            Point now = qu.poll();
            int nextX, nextY;
            for (int i = 0; i < 4; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];

                if (outOfBound(nextX, nextY)) {
                    continue;
                }

                if (graph[nextY][nextX] == 1) {
                    if (now.isDestroyed) {
                        continue;
                    }
                    visited[1][nextY][nextX] = visited[0][now.y][now.x] + 1;
                    qu.offer(new Point(nextX, nextY, true));
                    continue;
                }

                int idx = now.isDestroyed ? 1 : 0;

                if(visited[idx][nextY][nextX] <= visited[idx][now.y][now.x] + 1) {
                    continue;
                }

                visited[idx][nextY][nextX] = visited[idx][now.y][now.x] + 1;
                qu.offer(new Point(nextX, nextY, now.isDestroyed));
            }
        }
    }

    private static boolean outOfBound(int nextX, int nextY) {
        return 0 > nextX || nextX >= M || 0 > nextY || nextY >= N;
    }

    static class Point {
        int x,y;
        boolean isDestroyed;

        public Point(int x, int y, boolean isDestroyed) {
            this.x = x;
            this.y = y;
            this.isDestroyed = isDestroyed;
        }
    }

}
