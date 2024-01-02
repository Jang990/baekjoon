import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int y = Integer.parseInt(arr[0]);
        int x = Integer.parseInt(arr[1]);
        map = new String[y][x];
        visited = new boolean[y][x];
        for (int i = 0; i < y; i++) {
            map[i] = br.readLine().split("");
        }

        br.close();

        StringBuilder sb = new StringBuilder();
        int crossCnt = 0;
        for (int i = 1; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if(map[i][j].equals("."))
                    continue;

                int size = 1;
                while (search(size, i, j)) {
                    crossCnt++;
                    sb.append(i + 1).append(" ").append(j + 1)
                            .append(" ").append(size++).append("\n");
                }
            }
        }

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (map[i][j].equals("*") && visited[i][j] == false) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(crossCnt);
        System.out.println(sb);
    }

    private static boolean search(int size, int y, int x) {
        int[] crossX = {0, 0, size, -size};
        int[] crossY = {size, -size, 0, 0};
        for (int i = 0; i < 4; i++) {
            int searchingX = x + crossX[i];
            int searchingY = y + crossY[i];
            if(outOfBound(searchingX, searchingY)
                    || map[searchingY][searchingX].equals("."))
                return false;
        }

        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int searchingX = x + crossX[i];
            int searchingY = y + crossY[i];
            visited[searchingY][searchingX] = true;
        }

        return true;
    }

    private static boolean outOfBound(int searchingX, int searchingY) {
        return 0 > searchingX || searchingX >= map[0].length || 0 > searchingY || searchingY >= map.length;
    }
}
