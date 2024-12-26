import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static String[][] board;
    private static int[] dirX = {0, 0, 1, -1};
    private static int[] dirY = {1, -1, 0, 0};


    public static void main(String[] args) throws IOException {
        readBoard();
        Point start = readLocation();
        int result = countPerson(start);
        if (result == 0) {
            System.out.println("TT");
            return;
        }
        System.out.println(result);
    }

    private static int countPerson(Point start) {
        Queue<Point> qu = new LinkedList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        visited[start.y][start.x] = true;
        qu.offer(start);

        int result = 0;
        while (!qu.isEmpty()) {
            Point now = qu.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if(outOfBound(nextX, nextY)
                        || visited[nextY][nextX]
                        || board[nextY][nextX].equals("X"))
                    continue;
                if(board[nextY][nextX].equals("P"))
                    result++;
                visited[nextY][nextX] = true;
                qu.offer(new Point(nextX, nextY));
            }
        }

        return result;
    }

    private static boolean outOfBound(int x, int y) {
        return x < 0 || board[0].length <= x || y < 0 || board.length <= y;
    }

    private static Point readLocation() {
        Point p = null;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j].equals("I"))
                    p = new Point(j, i);
            }
        }
        return p;
    }

    private static void readBoard() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        board = new String[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().split("");
        }
        br.close();
    }
}
