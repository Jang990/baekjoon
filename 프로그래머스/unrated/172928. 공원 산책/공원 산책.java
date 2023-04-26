import java.util.Arrays;

class Solution {
    static String[][] map;
    static int nowX, nowY;
    public static int[] solution(String[] park, String[] routes) {
        map = Arrays.stream(park).map(p -> p.split("")).toArray(String[][]::new);
        String[][] directions = Arrays.stream(routes).map(p -> p.split(" ")).toArray(String[][]::new);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].equals("S")) {
                    nowX = j;
                    nowY = i;
                }
            }
        }

        for (int i = 0; i < directions.length; i++) {
            String direction =directions[i][0];
            int step = Integer.valueOf(directions[i][1]);
            if (direction.equals("E")) {
                moveEast(step);
            }
            else if (direction.equals("W")) {
                moveWest(step);
            }
            else if (direction.equals("S")) {
                moveSouth(step);
            }
            else if (direction.equals("N")) {
                moveNorth(step);
            }
        }

        return new int[] {nowY, nowX};
    }

    private static void moveNorth(int step) {
        int nextY = nowY;
        for (int i = 0; i < step; i++) {
            nextY--;
            if (checkNextLocation(nowX, nextY)) {
                continue;
            }
            nextY = nowY;
            break;
        }
        nowY = nextY;
    }

    private static void moveSouth(int step) {
        int nextY = nowY;
        for (int i = 0; i < step; i++) {
            nextY++;
            if (checkNextLocation(nowX, nextY)) {
                continue;
            }
            nextY = nowY;
            break;
        }
        nowY = nextY;

    }

    private static void moveEast(int step) {
        int nextX = nowX;
        for (int i = 0; i < step; i++) {
            nextX++;
            if (checkNextLocation(nextX, nowY)) {
                continue;
            }
            nextX = nowX;
            break;
        }
        nowX = nextX;
    }

    private static void moveWest(int step) {
        int nextX = nowX;
        for (int i = 0; i < step; i++) {
            nextX--;
            if (checkNextLocation(nextX, nowY)) {
                continue;
            }
            nextX = nowX;
            break;
        }
        nowX = nextX;
    }

    private static boolean checkNextLocation(int x, int y) {
        return 0 <= x && x < map[0].length && 0 <= y && y < map.length
                && !map[y][x].equals("X");
    }
}