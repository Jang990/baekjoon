import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int w;
    private static int h;
    private static int x;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.valueOf(st.nextToken());
        h = Integer.valueOf(st.nextToken());
        int n =Integer.valueOf(br.readLine());
        int[][] location = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            location[i][0] = Integer.valueOf(st.nextToken());
            location[i][1] = Integer.valueOf(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        br.close();

        int myLocation = Integer.valueOf(st.nextToken());
        x = Integer.valueOf(st.nextToken());

        switch (myLocation) {
            case 1:
                calc1(location);
                break;
            case 2:
                calc2(location);
                break;
            case 3:
                calc3(location);
                break;
            case 4:
                calc4(location);
                break;
        }

        System.out.println(result);
    }

    private static void calc4(int[][] location) {
        for (int[] target : location) {
            int targetX = target[1];
            int length = 0;
            switch (target[0]) {
                case 1:
                    length = (x + w - targetX);
                    break;
                case 2:
                    length = (h - x + w - targetX);
                    break;
                case 3:
                    length = Math.min(x + w + targetX, h - x + w + h - targetX);
                    break;
                case 4:
                    length = Math.abs(x - targetX);
                    break;
            }
            result += length;
        }
    }

    private static void calc3(int[][] location) {
        for (int[] target : location) {
            int targetX = target[1];
            int length = 0;
            switch (target[0]) {
                case 1:
                    length = (x + targetX);
                    break;
                case 2:
                    length = (h - x + targetX);
                    break;
                case 3:
                    length = Math.abs(x - targetX);
                    break;
                case 4:
                    length = Math.min(x + w + targetX, h - x + w + h - targetX);
                    break;
            }
            result += length;
        }
    }

    private static void calc2(int[][] location) {
        for (int[] target : location) {
            int targetX = target[1];
            int length = 0;
            switch (target[0]) {
                case 1:
                    length = Math.min(x + h + targetX, w - x + h + w - targetX);
                    break;
                case 2:
                    length = Math.abs(x - targetX);
                    break;
                case 3:
                    length = (x + h - targetX);
                    break;
                case 4:
                    length = (w - x + h - targetX);
                    break;
            }
            result += length;
        }
    }

    private static void calc1(int[][] location) {
        for (int[] target : location) {
            int targetX = target[1];
            int length = 0;
            switch (target[0]) {
                case 1:
                    length = Math.abs(x - targetX);
                    break;
                case 2:
                    length = Math.min(x + h + targetX, w - x + h + w - targetX);
                    break;
                case 3:
                    length = (x + targetX);
                    break;
                case 4:
                    length = (w - x + targetX);
                    break;
            }
            result += length;
        }
    }
}
