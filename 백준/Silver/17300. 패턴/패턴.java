import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited = new boolean[3][3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();

        Point current = toPoint(Integer.parseInt(st.nextToken()));
        visited[current.y][current.x] = true;
        while (st.hasMoreTokens()) {
            int loc = Integer.parseInt(st.nextToken());
            Point next = toPoint(loc);
            if (!canMove(current, next)) {
                System.out.println("NO");
                return;
            }
            current = next;
            visited[current.y][current.x] = true;
        }
        System.out.println("YES");
    }

    private static boolean canMove(Point current, Point next) {
        int xDiff = Math.abs(current.x - next.x);
        int yDiff = Math.abs(current.y - next.y);

        // 이동 x
        if(xDiff + yDiff == 0)
            return false;

        // x축 이동
        if (xDiff == 0 && yDiff != 0) {
            if (yDiff == 1)
                return !visited[next.y][next.x];
            if(current.y < next.y)
                return visited[next.y - 1][next.x] && !visited[next.y][next.x];
            else
                return visited[next.y + 1][next.x] && !visited[next.y][next.x];
        }

        // y축 이동
        if (xDiff != 0 && yDiff == 0) {
            if (xDiff == 1)
                return !visited[next.y][next.x];
            if(current.x < next.x)
                return visited[next.y][next.x - 1] && !visited[next.y][next.x];
            else
                return visited[next.y][next.x + 1] && !visited[next.y][next.x];
        }

        // 45도 이동
        if (xDiff == yDiff) {
            if(xDiff + yDiff == 2) // 45도 한 칸 이동
                return !visited[next.y][next.x];
            if(current.x < next.x && current.y < next.y)
                return visited[next.y - 1][next.x - 1] && !visited[next.y][next.x];
            if(current.x > next.x && current.y > next.y)
                return visited[next.y + 1][next.x + 1] && !visited[next.y][next.x];
            if(current.x < next.x && current.y > next.y)
                return visited[next.y + 1][next.x - 1] && !visited[next.y][next.x];
            else
                return visited[next.y - 1][next.x + 1] && !visited[next.y][next.x];
        }

        // 대각 이동
        return !visited[next.y][next.x];
    }

    private static Point toPoint(int loc) {
        int n = loc - 1;
        return new Point(n % 3, n / 3);
    }
}
