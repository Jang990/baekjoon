import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int movedX;
    private static int movedY;
    private static Point king;
    private static Point rock;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        king = convertLocationToPoint(st.nextToken());
        rock = convertLocationToPoint(st.nextToken());
        int n = Integer.valueOf(st.nextToken());
        for (int i = 0; i < n; i++) {
            String order = br.readLine();
            move(order, king);
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        sb.append(convertPointToLocation(king) + "\n");
        sb.append(convertPointToLocation(rock) + "\n");
        System.out.println(sb);
    }

    private static boolean move(String order, Point point) {
        movedX = 0;
        movedY = 0;

        if (order.contains("R")) {
            movedX++;
        }
        if (order.contains("L")) {
            movedX--;
        }
        if (order.contains("T")) {
            movedY++;
        }
        if (order.contains("B")) {
            movedY--;
        }

        int nextX = point.x + movedX;
        int nextY = point.y + movedY;
        if (isInOfBound(nextX, nextY)) {
            if (point == king && nextX == rock.x && nextY == rock.y) {
                if (!move(order, rock)) {
                    return false;
                }
            }

            point.x = nextX;
            point.y = nextY;
            return true;
        }

        return false;
    }

    private static boolean isInOfBound(int movedX, int movedY) {
        return 1 <= movedX && movedX <= 8 && 1 <= movedY && movedY <= 8;
    }

    private static String convertPointToLocation(Point point) {
        char x = (char)(point.x - 1 + 'A');
        char y = (char)(point.y + '0');
        return x + "" + y;
    }

    private static Point convertLocationToPoint(String location) {
        int x = location.charAt(0) - 'A' + 1;
        int y = location.charAt(1) - '0';
        return new Point(x, y);
    }
}
