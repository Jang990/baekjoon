import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] room;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] options = br.readLine().split(" ");
        int N = Integer.parseInt(options[0]);
        int M = Integer.parseInt(options[1]);

        options = br.readLine().split(" ");
        int nowY = Integer.parseInt(options[0]);
        int nowX = Integer.parseInt(options[1]);
        int nowDirection = Integer.parseInt(options[2]);


        room = new int[N][M];
        for (int i = 0; i < N; i++) {
            room[i] = Arrays.stream(br.readLine()
                    .split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        br.close();


        Robot robot = new Robot(nowX, nowY, nowDirection);

        int result = 0;
        while (true) {
            // 동작 시작
            if (robot.cleanUp(room)) {
                result++;
                continue;
            }

            if(!checkDirtyPlace(robot.x, robot.y)) {
                boolean stop = !robot.moveBackward();
                if(stop) {
                    break;
                }
                continue;
            }

            robot.turnAround();
            if(robot.isDirtyPlaceAhead(room)) {
                robot.moveForward();
            }
        }

        System.out.println(result);
    }

    private static boolean checkDirtyPlace(int nowX, int nowY) {
        int[] dirX = {0, 0, 1, -1};
        int[] dirY = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int nextX = nowX + dirX[i];
            int nextY = nowY + dirY[i];
            if (outOfBoundary(nextX, nextY)) {
                continue;
            }
            int nextStatus = room[nextY][nextX];
            if (nextStatus == 0) {
                return true;
            }
        }
        return false;
    }

    static class Robot {
        public int x, y, direction;

        public Robot(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        // 90 반시계 회전
        public void turnAround() {
            direction--;
            if (direction == -1) {
                direction = 3;
            }
        }

        // 전진
        public boolean moveForward() {
            int nextX = getForwardX(), nextY = getForwardY();

            if (checkNext(nextX, nextY)) {
                return false;
            }

            x = nextX;
            y = nextY;
            return true;
        }

        // 후진
        public boolean moveBackward() {
            int nextX = getBackwardX(), nextY = getBackwardY();
            if (checkNext(nextX, nextY)) {
                return false;
            }

            x = nextX;
            y = nextY;
            return true;
        }

        public boolean cleanUp(int[][] room) {
            if(room[y][x] == 0) {
                room[y][x] = Integer.MAX_VALUE;
                return true;
            }

            return false;
        }

        public boolean isDirtyPlaceAhead(int[][] room) {
            int nextX = getForwardX(), nextY = getForwardY();

            if (checkNext(nextX, nextY)) {
                return false;
            }

            if (room[nextY][nextX] == 0) {
                return true;
            }
            else {
                return false;
            }
        }

        private boolean checkNext(int nextX, int nextY) {
            return (nextX == x && nextY == y) || outOfBoundary(nextX, nextY) || room[nextY][nextX] == 1;
        }

        private int getBackwardX() {
            int nextX = x;
            if (direction == 1 && x > 0) {
                nextX--;
            }
            else if (direction == 3 && x < room[0].length - 1) {
                nextX++;
            }
            return nextX;
        }

        private int getBackwardY() {
            int nextY = y;
            if (direction == 0 && y < room.length - 1) {
                nextY++;
            }

            else if (direction == 2 && y > 0) {
                nextY--;
            }

            return nextY;
        }

        private int getForwardX() {
            int nextX = x;
            if (direction == 3 && x > 0) {
                nextX--;
            }
            else if (direction == 1 && x < room[0].length - 1) {
                nextX++;
            }
            return nextX;
        }

        private int getForwardY() {
            int nextY = y;
            if (direction == 2 && y < room.length - 1) {
                nextY++;
            }
            else if (direction == 0 && y > 0) {
                nextY--;
            }
            return nextY;
        }
    }



    private static boolean outOfBoundary(int nowX, int nowY) {
        return (0 > nowX || nowX >= room[0].length || 0 > nowY || nowY >= room.length);
    }
}
