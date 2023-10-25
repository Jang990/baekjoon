import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    static int[][] map;
    static boolean[][] visited;

    static final int EXTERNAL_SPACE = -1;
    static final int EMPTY_SPACE = 0;
    static final int CHEESE = 1;

    static int[] dirX = {0, 0, 1, -1};
    static int[] dirY = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::valueOf).toArray();
        }

        br.close();

        boolean existCheese = true;
        int answer = 0;
        checkExternalSpace();

        while (existCheese) {
            Queue<Point> exposedCheeseLocation = checkExposedCheese();
            melt(exposedCheeseLocation);
            existCheese = checkExposingSpace(exposedCheeseLocation);
            answer++;
        }

        System.out.println(answer);
    }

    private static Queue<Point> checkExposedCheese() {
        Queue<Point> exposedCheeseLocation = new LinkedList<>();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] != CHEESE)
                    continue;

                if (isExposed(y, x)) {
                    exposedCheeseLocation.offer(new Point(x, y));
                }
            }
        }

        return exposedCheeseLocation;
    }

    private static boolean isExposed(int y, int x) {
        int targetX, targetY, exposedPlaneCnt = 0;
        for (int k = 0; k < 4; k++) {
            targetX = x + dirX[k];
            targetY = y + dirY[k];

            if (outOfBound(targetX, targetY) || map[targetY][targetX] != EXTERNAL_SPACE)
                continue;

            exposedPlaneCnt++;
        }

        return exposedPlaneCnt >= 2;
    }

    private static void melt(Queue<Point> exposedCheeseLocation) {
        List<Point> locationList = exposedCheeseLocation.stream().collect(Collectors.toList());
        for (Point location : locationList) {
            map[location.y][location.x] = EXTERNAL_SPACE;
        }
    }

    private static void checkExternalSpace() {
        Queue<Point> qu = new LinkedList<>();
        visited = new boolean[map.length][map[0].length];
        visited[0][0] = true;
        map[0][0] = -1;
        qu.offer(new Point(0, 0));

        while (!qu.isEmpty()) {
            Point now = qu.poll();
            int nextX, nextY;
            for (int i = 0; i < 4; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];

                if (outOfBound(nextX, nextY) || visited[nextY][nextX] || map[nextY][nextX] != EMPTY_SPACE)
                    continue;

                qu.offer(new Point(nextX, nextY));
                map[nextY][nextX] = EXTERNAL_SPACE;
                visited[nextY][nextX] = true;
            }
        }
    }

    private static boolean checkExposingSpace(Queue<Point> exposedSpace) {
        boolean existCheese = false;
        while (!exposedSpace.isEmpty()) {
            Point now = exposedSpace.poll();

            int nextX, nextY;
            for (int i = 0; i < 4; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];

                if(!existCheese && map[nextY][nextX] == CHEESE)
                    existCheese = true;

                if (outOfBound(nextX, nextY) || visited[nextY][nextX] || map[nextY][nextX] != EMPTY_SPACE)
                    continue;

                exposedSpace.offer(new Point(nextX, nextY));
                map[nextY][nextX] = EXTERNAL_SPACE;
            }
        }

        return existCheese;
    }

    private static boolean outOfBound(int nextX, int nextY) {
        return 0 > nextX || nextX >= map[0].length || 0 > nextY || nextY >= map.length ;
    }
}
