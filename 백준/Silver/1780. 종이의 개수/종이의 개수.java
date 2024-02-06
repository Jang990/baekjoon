import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] papers;
    static int[] result = new int[3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        papers = new int[n][n];
        for (int i = 0; i < n; i++) {
            papers[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        rec(0, 0, n);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void rec(int x, int y, int size) {
        if (size == 1 || isSameAll(x, y, size)) {
            result[papers[y][x] + 1]++;
            return;
        }

        int nextSize = size / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rec(x + j * nextSize, y + i * nextSize, nextSize);
            }
        }
    }

    private static boolean isSameAll(int x, int y, int size) {
        int num = papers[y][x];
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (num != papers[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
