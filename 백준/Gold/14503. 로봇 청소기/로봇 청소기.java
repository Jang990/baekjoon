import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] graph;
    static final int CLEAN = 2;
    static final int WALL = 1;
    static final int EMPTY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] cond = readLine(br);
        graph = new int[cond[0]][cond[1]];
        cond = readLine(br);

        Robot robot = new Robot(cond[1], cond[0], cond[2]);
        for (int i = 0; i < graph.length; i++)
            graph[i] = readLine(br);
        br.close();

        while (robot.process());
        System.out.println(robot.cleanCnt);
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }


    static class Robot {
        static final int[] dirX = {0, 1, 0, -1};
        static final int[] dirY = {-1, 0, 1, 0};
        int x, y, dir, cleanCnt;

        public Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            cleanCnt = 0;
        }

        public boolean process() {
            if (graph[y][x] == EMPTY) {
                clean();
                return true;
            }

            if (existNearByEmptySpace()) {
                turn();
                while (!isForwardEmpty()) {
                    turn();
                }
                move();
                return true;
            }

            if(moveBackward())
                return true;
            return false;
        }

        private boolean moveBackward() {
            int backDir = (dir + 2) % 4;
            int nextX = x + dirX[backDir];
            int nextY = y + dirY[backDir];

            if(outOfBound(nextX, nextY) || graph[nextY][nextX] == WALL)
                return false;

            x = nextX;
            y = nextY;
            return true;
        }

        private void move() {
            x += dirX[dir];
            y += dirY[dir];
        }

        private boolean isForwardEmpty() {
            int forwardX = x + dirX[dir];
            int forwardY = y + dirY[dir];
            if(outOfBound(forwardX, forwardY))
                return false;

            return graph[forwardY][forwardX] == EMPTY;
        }

        private void turn() {
            dir--;
            if(dir < 0)
                dir = 3;
        }

        private boolean existNearByEmptySpace() {
            for (int i = 0; i < 4; i++) {
                int nextX = x + dirX[i];
                int nextY = y + dirY[i];
                if(outOfBound(nextX, nextY))
                    continue;
                if(graph[nextY][nextX] == EMPTY)
                    return true;
            }
            return false;
        }

        private void clean() {
            graph[y][x] = CLEAN;
            cleanCnt++;
        }
    }

    private static boolean outOfBound(int x, int y) {
        return x < 0 || graph[0].length <= x || y < 0 || graph.length <= y;
    }
}
