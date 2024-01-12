import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int max = 0;
    private static int[][] paper;
    private static int[][] paperNumber;
    private static int paperCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        paper = new int[Integer.parseInt(arr[0])][Integer.parseInt(arr[1])];
        paperNumber = new int[Integer.parseInt(arr[0])][Integer.parseInt(arr[1])];
        initNumber(paperNumber);
        paperCnt = paper.length * paper[0].length;
        for (int i = 0; i < paper.length; i++) {
            paper[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        rec(0,0);
        System.out.println(max);
    }

    private static void initNumber(int[][] paperNumber) {
        for (int i = 0; i < paperNumber.length; i++) {
            for (int j = 0; j < paperNumber[0].length; j++) {
                paperNumber[i][j] = i * paperNumber[0].length + j;
            }
        }
    }

    private static void rec(int loc, int sum) {
        if (loc >= paperCnt) {
            max = Math.max(max, sum);
            return;
        }
        Point p = getLocation(loc);
        if (paperNumber[p.y][p.x] != getNumber(p.x, p.y)) {
            rec(loc + 1, sum);
            return;
        }


        int i = 0;
        int now = paper[p.y][p.x];
        int next = now;
        rec(loc+1, sum + next);

        for (i = 0; i + p.x + 1 < paper[0].length; i++) {
            if (paperNumber[p.y][p.x + i + 1] != getNumber(p.x + i + 1, p.y))
                break;
            paperNumber[p.y][p.x + i + 1] = getNumber(p.x, p.y);
            next = next * 10 + paper[p.y][p.x + i + 1];
            rec(loc + 1, sum + next);
        }
        cleanX(p, i);

        next = now;
        for (i = 0; i + p.y + 1 < paper.length; i++) {
            if (paperNumber[p.y + i + 1][p.x] != getNumber(p.x, p.y + i + 1))
                break;
            paperNumber[p.y + i + 1][p.x] = getNumber(p.x, p.y);
            next = next * 10 + paper[p.y + i + 1][p.x];
            rec(loc + 1, sum + next);
        }
        cleanY(p, i);
    }

    private static void cleanX(Point p, int i) {
        for (int j = 0; j < i; j++) {
            paperNumber[p.y][p.x + j + 1] = getNumber(p.x + j + 1, p.y);
        }
    }

    private static void cleanY(Point p, int i) {
        for (int j = 0; j < i; j++) {
            paperNumber[p.y + j + 1][p.x] = getNumber(p.x, p.y + j + 1);
        }
    }

    private static Point getLocation(int loc) {
        return new Point(loc % paper[0].length,loc / paper[0].length);
    }

    private static int getNumber(int x, int y) {
        return x + y * paper[0].length;
    }

}
