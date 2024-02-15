import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static boolean[][] isHole;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        br.close();
        isHole = new boolean[n][n];
        rec(n, 0, 0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(isHole[i][j])
                    sb.append(" ");
                else
                    sb.append("*");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void rec(int size, int x, int y) {
        if (size <= 3) {
            createHole(1, x, y);
            return;
        }

        int nextSize = size / 3;
        createHole(nextSize, x, y);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i == 1 && j == 1) continue;
                rec(nextSize, x + nextSize * i, y + nextSize * j);
            }
        }
    }

    private static void createHole(int holeSize, int x, int y) {
        for (int i = 0; i < holeSize; i++) {
            for (int j = 0; j < holeSize; j++) {
                isHole[i + y + holeSize][j + x + holeSize] = true;
            }
        }
    }
}
