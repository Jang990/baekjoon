import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String[][] graph;
    static Point goal;
    static Bead blue, red;
    static int[] dirX = {0, 0, 1, -1},
            dirY = {1, -1, 0, 0};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        graph = new String[n][m];

        for (int i = 0; i < n; i++) {
            graph[i] = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                if(graph[i][j].equals("#"))
                    continue;

                if(graph[i][j].equals("B"))
                    blue = new Bead(new Point(j, i));
                else if(graph[i][j].equals("R"))
                    red = new Bead(new Point(j, i));
                else if(graph[i][j].equals("O"))
                    goal = new Point(j, i);
            }
        }
        br.close();

        blue.setOtherBead(red);
        red.setOtherBead(blue);

        rec(0);

        if(min == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(min);
    }

    private static void rec(int depth) {
        if(depth > 10)
            return;

        if (!blue.goal() && red.goal()) {
            min = Math.min(min, depth);
            return;
        }

        if(blue.goal())
            return;

        for (int i = 0; i < 4; i++) {
            blue.initDir(i);
            red.initDir(i);
            int[] tempB = {blue.loc.x, blue.loc.y};
            int[] tempR = {red.loc.x, red.loc.y};

            while (blue.canMove() || red.canMove()) {
                blue.move();
                red.move();
            }

            rec(depth+1);

            blue.initLocation(tempB);
            red.initLocation(tempR);
        }
    }

    static class Bead {
        Point loc;
        Bead otherBead;
        int dirIdx;

        public Bead(Point loc) {
            this.loc = loc;
        }

        public void setOtherBead(Bead otherBead) {
            this.otherBead = otherBead;
        }

        void initLocation(int[] arr) {
            loc.x = arr[0];
            loc.y = arr[1];
        }

        void initDir(int idx) {
            dirIdx = idx;
        }

        boolean move() {
            if(!canMove())
                return false;

            if(goal())
                return false;

            loc.x += dirX[dirIdx];
            loc.y += dirY[dirIdx];
            return true;
        }

        boolean canMove() {
            int nextX = loc.x + dirX[dirIdx];
            int nextY = loc.y + dirY[dirIdx];

            if (goal()) {
                return false;
            }
            if (graph[nextY][nextX].equals("#")) {
                return false;
            }
            if (otherBead.isSameLoc(nextX, nextY)) {
                return otherBead.canMove() || otherBead.goal();
            }
            return true;
        }

        boolean isSameLoc(int x, int y) {
            return loc.x == x && loc.y == y;
        }

        boolean goal() {
            return loc.equals(goal);
        }
    }
}
