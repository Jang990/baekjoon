import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int N;
    static int L;
    static int R;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        int day = 0;
        boolean isWorking;
        while (true) {
            visited = new boolean[N][N];
            isWorking = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) {
                        continue;
                    }

                    boolean check = BFS(j, i);
                    if (check) {
                        isWorking = true;
                    }
                }
            }

            if (!isWorking) {
                break;
            }
            day++;
        }


        System.out.println(day);
    }

    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};
    private static boolean BFS(int x, int y) {
        Queue<Point> qu = new LinkedList<>();
        List<Point> united = new ArrayList<>();
        Point p = new Point(x,y);
        visited[y][x] = true;
        qu.offer(p);

        while (!qu.isEmpty()) {
            Point now = qu.poll();
            united.add(now);
            int nextX, nextY;
            for (int i = 0; i < 4; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];

                if (outOfBound(nextX, nextY) || visited[nextY][nextX]) {
                    continue;
                }

                if (checkBoundaryOpen(map[now.y][now.x], map[nextY][nextX])) {
                    qu.offer(new Point(nextX,nextY));
                    visited[nextY][nextX] = true;
                }
            }
        }

        if (united.size() <= 1) {
            return false;
        }

        int avg = getAvgUnited(united);
        setUnitedPopulation(united, avg);
        return true;
    }

    private static void setUnitedPopulation(List<Point> united, int avg) {
        for (Point point : united) {
            map[point.y][point.x] = avg;
        }
    }

    private static int getAvgUnited(List<Point> united) {
        int sum = 0;
        for (Point point : united) {
            sum += map[point.y][point.x];
        }

        return sum / united.size();
    }

    private static boolean outOfBound(int nextX, int nextY) {
        return 0 > nextX || nextX >= map[0].length || 0 > nextY || nextY >= map.length;
    }

    private static boolean checkBoundaryOpen(int country1, int country2) {
        int num = Math.abs(country1 - country2);
        return L <= num && num <= R;
    }
}
