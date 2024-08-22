import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String[] commands = br.readLine().split("");
            Turtle turtle = new Turtle();
            for (String command : commands) {
                turtle.order(command);
            }
            sb.append(turtle.getRectangleSize()).append("\n");
        }
        br.close();

        System.out.println(sb);
    }

    static class Turtle {
        private final static int[] dirX = {0,1, 0,-1},
                dirY = {-1, 0, 1, 0};

        private int dir = 0;
        private int currentX = 0, currentY = 0;
        private int maxX = 0,minX = 0,
                minY = 0, maxY = 0;


        public void order(String c) {
            if(c.equals("F"))
                forward();
            else if(c.equals("B"))
                backward();
            else if(c.equals("L"))
                turnLeft();
            else
                turnRight();
        }

        private void backward() {
            currentX += reverse(dirX[dir]);
            currentY += reverse(dirY[dir]);

            checkX();
            checkY();
        }

        private int reverse(int num) {
            return num * -1;
        }

        private void forward() {
            currentX += dirX[dir];
            currentY += dirY[dir];

            checkX();
            checkY();
        }

        private void checkY() {
            minY = Math.min(minY, currentY);
            maxY = Math.max(maxY, currentY);
        }

        private void checkX() {
            minX = Math.min(minX, currentX);
            maxX = Math.max(maxX, currentX);
        }

        private void turnLeft() {
            if(dir - 1 < 0)
                dir = dirX.length - 1;
            else
                dir = dir - 1;
        }

        private void turnRight() {
            dir = (dir + 1) % dirX.length;
        }

        public int getRectangleSize() {
            return (maxX - minX) * (maxY - minY);
        }
    }
}
