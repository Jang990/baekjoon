import java.io.*;

public class Main {

    private static boolean[] ballColors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        String[] balls = br.readLine().split("");
        br.close();

        ballColors = new boolean[size];
        for (int i = 0; i < size; i++)
            ballColors[i] = balls[i].equals("R");

        int result = Integer.MAX_VALUE;
        result = Math.min(result, moveForward(true));
        result = Math.min(result, moveBackward(true));
        result = Math.min(result, moveForward(false));
        result = Math.min(result, moveBackward(false));

        System.out.println(result);
    }

    private static int moveForward(boolean color) {
        int moveCnt = 0;
        int idx = 0;
        while (idx < ballColors.length && ballColors[idx] == color)
            idx++;

        while (idx < ballColors.length) {
            if(ballColors[idx] == color)
                moveCnt++;
            idx++;
        }

        return moveCnt;
    }

    private static int moveBackward(boolean color) {
        int moveCnt = 0;
        int idx = ballColors.length - 1;
        while (0 <= idx && ballColors[idx] == color)
            idx--;

        while (0 <= idx) {
            if(ballColors[idx] == color)
                moveCnt++;
            idx--;
        }

        return moveCnt;
    }
}
