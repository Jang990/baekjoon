import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int n;
    private static int[] balloons;
    private static boolean[] isExploded;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        balloons = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        isExploded = new boolean[n];
        br.close();

        StringBuilder sb = new StringBuilder();
        sb.append("1 ");
        int location = 0;
        while (true) {
            isExploded[location] = true;
            if(isAllExploded())
                break;
            location = move(location);
            sb.append(location + 1).append(" ");
        }
        System.out.println(sb);
    }

    private static int move(int location) {
        int result = location;
        int movement = balloons[location];
        if (movement > 0) {
            while (movement > 0) {
                result = moveForward(result);
                movement--;
            }
            return result;
        }

        while (movement < 0) {
            result = moveBackward(result);
            movement++;
        }
        return result;
    }

    private static int moveBackward(int location) {
        int result = location;
        do {
            result--;
            if(result < 0)
                result = n - 1;
        } while (isExploded[result % n]);
        return result;
    }

    private static int moveForward(int location) {
        int result = location;
        do {
            result++;
            if (result >= n)
                result = 0;
        } while (isExploded[result]);
        return result;
    }

    private static boolean isAllExploded() {
        for (int i = 0; i < n; i++) {
            if(isExploded[i]) continue;
            return false;
        }
        return true;
    }
}
