import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    private static int[][] map;
    private static Shark shark;
    private static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        initShark();

        while (true) {
            List<Point> fishList = bfs();
            if(fishList.isEmpty())
                break;
            Point fish = selectFish(fishList);
            shark.eat(fish);
        }

        System.out.println(time);
    }

    private static Point selectFish(List<Point> fishList) {
        Point selected = new Point(map.length, map.length);
        for (Point fish : fishList) {
            if(selected.y < fish.y)
                continue;
            if(selected.y == fish.y && selected.x < fish.x)
                continue;
            selected = fish;
        }
        return selected;
    }


    static int[] dirX = {0,-1,1,0};
    static int[] dirY = {-1,0,0,1};
    private static List<Point> bfs() {
        Queue<Point> qu = new LinkedList<>();
        int[][] visited = new int[map.length][map[0].length];
        int min = Integer.MAX_VALUE;
        qu.offer(shark.loc);
        visited[shark.loc.y][shark.loc.x] = 1;
        List<Point> fishList = new LinkedList<>();

        while (!qu.isEmpty()) {
            Point now = qu.poll();
            int nowTime = visited[now.y][now.x];

            int nextX, nextY;
            for (int i = 0; i < 4; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];
                Point fish = new Point(nextX, nextY);
                if(isOutOfBound(fish) ||
                        visited[fish.y][fish.x] != 0 ||
                        !shark.isMovable(fish))
                    continue;

                if(min < nowTime)
                    continue;

                if (map[fish.y][fish.x] != 0 && shark.isSmaller(fish)) {
                    min = nowTime;
                    fishList.add(fish);
                }

                visited[fish.y][fish.x] = nowTime + 1;
                qu.offer(fish);
            }
        }

        if (min != Integer.MAX_VALUE)
            time += min;

        return fishList;
    }

    private static boolean isOutOfBound(Point p) {
        return 0 > p.x || p.x >= map.length || 0 > p.y || p.y >= map.length;
    }

    private static void initShark() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    shark = new Shark(new Point(j, i));
                }
            }
        }
    }

    static class Shark {
        private int size = 2;
        private int hunger = 0;
        private Point loc;

        public Shark(Point loc) {
            this.loc = loc;
        }

        public void eat(Point fish) {
            hunger++;
            map[fish.y][fish.x] = 0;
            loc = fish;

            if (hunger >= size) {
                size++;
                hunger = 0;
            }
        }

        public boolean isSmaller(Point fish) {
            return map[fish.y][fish.x] < size;
        }

        public boolean isMovable(Point fish) {
            return map[fish.y][fish.x] <= size;
        }
    }

}
