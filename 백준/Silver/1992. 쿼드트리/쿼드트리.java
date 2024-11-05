import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] image;
    static StringBuilder result = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        initImage(br, size);
        br.close();
        Point startPoint = new Point(0, 0);
        if (isAllSame(startPoint, size, 0)) {
            System.out.println(0);
            return;
        }
        if (isAllSame(startPoint, size, 1)) {
            System.out.println(1);
            return;
        }

        compress(startPoint, size);

        System.out.println(result.toString());
    }

    private static boolean isAllSame(Point loc, int size, int pixel) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(image[loc.y + i][loc.x + j] != pixel)
                    return false;
            }
        }
        return true;
    }

    private static void compress(Point loc, int size) {
        result.append("(");
        int currentPixel = image[loc.y][loc.x];
        int nextSize = size / 2;
        if (size == 1) {
            result.append(currentPixel);
            return;
        }

        int[] dirX = {0, nextSize,0, nextSize};
        int[] dirY = {0,0, nextSize, nextSize};
        for (int i = 0; i < 4; i++) {
            Point next = new Point(loc.x + dirX[i], loc.y + dirY[i]);
            int nextPixel = image[next.y][next.x];
            if (isAllSame(next, nextSize, nextPixel)) {
                result.append(nextPixel);
                continue;
            }

            compress(next, nextSize);
        }
        result.append(")");
    }

    private static void initImage(BufferedReader br, int size) throws IOException {
        image = new int[size][size];
        for (int i = 0; i < size; i++) {
            image[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }
}
