import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    private static String[][] room = new String[5][5];
    private static boolean[][] visited;
    private static int result = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            room[i] = br.readLine().split("");
        }
        br.close();

        visited = new boolean[5][5];

        combine(0, 0, new int[7]);

        System.out.println(result);
    }

    private static void combine(int depth, int choice, int[] buf) {
        if (choice == buf.length) {
            if(isLinked7Princess(buf))
                result++;
            return;
        }

        if(depth == 25)
            return;

        combine(depth+1, choice, buf);
        buf[choice] = depth;
        combine(depth+1, choice+1, buf);
    }

    static int[] dirX = {0, 0, 1, -1},
            dirY = {1, -1, 0, 0};

    private static boolean isLinked7Princess(int[] buf) {
        Queue<Point> qu = new LinkedList<>();
        List<Point> list = convert(buf);
        Point start = convertPoint(buf[0]);
        qu.offer(start);

        int linkedPrincess = 0;
        int som = 0;

        while (!qu.isEmpty()) {
            Point now = qu.poll();
            int nextX, nextY;
            for (int i = 0; i < 4; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];
                Point next = new Point(nextX, nextY);
                if (!list.contains(next)) {
                    continue;
                }

                list.remove(next);
                qu.offer(next);
                linkedPrincess++;
                if(room[nextY][nextX].equals("S"))
                    som++;
            }
        }

        if(linkedPrincess != 7 || som < 4)
            return false;

        return true;
    }

    private static LinkedList<Point> convert(int[] buf) {
        LinkedList<Point> list = new LinkedList<>();
        for (int i = 0; i < buf.length; i++) {
            list.add(convertPoint(buf[i]));
        }
        return list;
    }

    private static Point convertPoint(int buf) {
        return new Point(buf % 5, buf / 5);
    }
}
