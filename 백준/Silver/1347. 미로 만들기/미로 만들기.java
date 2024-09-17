import java.awt.*;
import java.io.*;

public class Main {
    static final int LEFT = 0, RIGHT = 1, FORWARD = 2;
    static final int WEST = 0, NORTH = 1, EAST = 2, SOUTH = 3;
    static final int[] dirX = {-1, 0, 1 , 0},
            dirY = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split("");
        br.close();
        HongJun hongJun = new HongJun();
        for (String commandStr : line) {
            hongJun.move(command(commandStr));
        }

        hongJun.print();
    }

    private static int command(String commandStr) {
        if(commandStr.equals("L"))
            return LEFT;
        else if(commandStr.equals("R"))
            return RIGHT;
        else
            return FORWARD;
    }

    static class HongJun {
        private Point location;
        private int dir;
        private boolean[][] visited;


        public HongJun() {
            location = new Point(49, 49);
            visited = new boolean[99][99];
            visited[location.y][location.x] = true;
            dir = NORTH;
        }

        public void move(int command) {
            if(command == FORWARD)
                go();
            else
                turn(command);
        }

        private void go() {
            location.move(location.x + dirX[dir], location.y + dirY[dir]);
            visited[location.y][location.x] = true;
        }

        private void turn(int command) {
            if (command == LEFT) {
                dir = (dir + 1) % 4;
                return;
            }

            dir--;
            if(dir < 0)
                dir = 3;
        }

        public void print() {
            printArea(
                    ArrayAnalyzer.leftTop(visited),
                    ArrayAnalyzer.rightBottom(visited)
            );
        }

        private void printArea(Point leftTop, Point rightBottom) {
            StringBuilder sb = new StringBuilder();
            for (int y = leftTop.y; y <= rightBottom.y; y++) {
                for (int x = leftTop.x; x <= rightBottom.x; x++) {
                    if(visited[y][x])
                        sb.append(".");
                    else
                        sb.append("#");
                }
                sb.append("\n");
            }

            System.out.println(sb);
        }

    }

    static class ArrayAnalyzer {

        public static Point leftTop(boolean[][] visited) {
            return new Point(findLeftX(visited), findTopY(visited));
        }

        public static Point rightBottom(boolean[][] visited) {
            return new Point(findRightX(visited), findBottomY(visited));
        }

        private static int findBottomY(boolean[][] visited) {
            int result = Integer.MIN_VALUE;
            for (int x = visited[0].length - 1; x >= 0; x--) {
                for (int y = visited.length - 1; y >= 0; y--) {
                    if(!visited[y][x])
                        continue;
                    result = Math.max(result, y);
                    break;
                }
            }
            return result;
        }

        private static int findRightX(boolean[][] visited) {
            int result = Integer.MIN_VALUE;
            for (int y = visited.length - 1; y >= 0; y--) {
                for (int x = visited[0].length - 1; x >= 0; x--) {
                    if(!visited[y][x])
                        continue;
                    result = Math.max(result, x);
                    break;
                }
            }
            return result;
        }

        private static int findTopY(boolean[][] visited) {
            int result = Integer.MAX_VALUE;
            for (int x = 0; x < visited.length; x++) {
                for (int y = 0; y < visited.length; y++) {
                    if(!visited[y][x])
                        continue;
                    result = Math.min(result, y);
                    break;
                }
            }
            return result;
        }

        private static int findLeftX(boolean[][] visited) {
            int result = Integer.MAX_VALUE;
            for (int y = 0; y < visited.length; y++) {
                for (int x = 0; x < visited.length; x++) {
                    if (!visited[y][x])
                        continue;
                    result = Math.min(result, x);
                    break;
                }
            }
            return result;
        }
    }


}
