import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] paper;
    static int whiteCnt = 0;
    static int blueCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = readSize(br);
        paper = readPaper(br, size);
        br.close();

        divide(0, 0, size);

        System.out.println(whiteCnt);
        System.out.println(blueCnt);
    }

    private static void divide(int x, int y, int size) {
        if (size == 1 || isAllSameColor(x, y, size)) {
            plusPaper(paper[y][x]);
            return;
        }

        int nextSize = size / 2;
        divide(x, y, nextSize);
        divide(x, y + nextSize, nextSize);
        divide(x + nextSize, y, nextSize);
        divide(x + nextSize, y + nextSize, nextSize);
    }

    private static boolean isAllSameColor(int x, int y, int size) {
        int baseColor = paper[y][x];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(baseColor != paper[y+i][x+j]) return false;
            }
        }
        return true;
    }

    private static void plusPaper(int color) {
        if(color == 1)
            blueCnt++;
        else
            whiteCnt++;
    }

    private static int[][] readPaper(BufferedReader br, int size) throws IOException {
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            result[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        return result;
    }

    private static int readSize(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }
}
