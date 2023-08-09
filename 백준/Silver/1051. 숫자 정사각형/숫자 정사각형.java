import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int maxLength = 1;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());
        maxLength = Math.min(N, M);
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::valueOf).toArray();
        }
        br.close();

        int longestLength = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                longestLength = Math.max(getMaxRectangle(longestLength, i, j), longestLength);
            }
        }

        if (longestLength == 0) {
            System.out.println(1);
        }
        else {
            longestLength++;
            System.out.println(longestLength*longestLength);
        }
    }

    private static int getMaxRectangle(int longestLength, int y, int x) {
        int nowLongestLength = 1;
        for (int length = longestLength; length <= maxLength; length++) {
            try {
                int n1 = map[y][x];
                int n2 = map[y + length][x];
                int n3 = map[y][x + length];
                int n4 = map[y + length][x + length];

                if (n1 == n2 && n2 == n3 && n3 == n4) {
                    nowLongestLength = length;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }

        return nowLongestLength;
    }
}
