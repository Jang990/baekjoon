import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new int[n+1][n+1];
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            graph[y][x] = 1;
        }

        int order = Integer.parseInt(br.readLine());
        Snake snake = new Snake(1, 1);
        for (int i = 0; i < order; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            snake.order(time, dir);
        }
        br.close();

        snake.order(Integer.MAX_VALUE, null);

        System.out.println(snake.getTimer());
    }

    static class Snake {
        private int id;
        private Point head, tail;
        private boolean isDead;
        private int timer;
        private int dirIdx;
        private int[][] dirHistory;

        private static final int[] dirX = {1, 0, -1, 0},
                dirY = {0, 1, 0, -1};

        public Snake(int startX, int startY) {
            this.id = -1;
            this.head = new Point(startX, startY);
            this.tail = new Point(startX, startY);
            graph[startY][startX] = id;
            dirIdx = 0;
            timer = 0;
            isDead = false;
            dirHistory = new int[graph.length][graph[0].length];
            recordHistory();
        }

        private void recordHistory() {
            dirHistory[head.y][head.x] = dirIdx;
        }

        public boolean order(int timeout, String dir) {
            if(isDead)
                return false;
            for (int i = timer; i < timeout; i++) {
                move();
                if(isDead)
                    return false;
            }

            changeDirection(dir);
            return true;
        }

        private void changeDirection(String dir) {
            if (dir.equals("L")) {
                if (dirIdx - 1 < 0)
                    dirIdx = 4;
                dirIdx--;
            } else {
                if(dirIdx + 1 >= 4)
                    dirIdx = -1;
                dirIdx++;
            }
            recordHistory();
        }

        private void move() {
            timer++;
            int nextX = head.x + dirX[dirIdx];
            int nextY = head.y + dirY[dirIdx];
            if (isCollision(nextX, nextY)) {
                isDead = true;
                return;
            }

            boolean isApple = graph[nextY][nextX] == 1;
            moveHead(nextX, nextY);
            if (isApple)
                return;
            moveTail();
        }

        private void moveHead(int nextX, int nextY) {
            head.x = nextX;
            head.y = nextY;
            graph[head.y][head.x] = id;
            recordHistory();
        }

        private void moveTail() {
            int movedDirIdx = dirHistory[tail.y][tail.x];
            int nextX = tail.x + dirX[movedDirIdx],
                    nextY = tail.y + dirY[movedDirIdx];

            graph[tail.y][tail.x] = 0;
            tail.x = nextX;
            tail.y = nextY;
        }

        private boolean isCollision(int nextX, int nextY) {
            return isOutOfBound(nextX, nextY) || graph[nextY][nextX] == id;
        }

        public int getTimer() {
            return timer;
        }
    }

    static boolean isOutOfBound(int x, int y) {
        return 0 >= x || x >= graph[0].length
                || 0 >= y || y >= graph.length;
    }
}
