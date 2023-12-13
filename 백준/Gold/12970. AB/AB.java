import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        br.close();
        int len = Integer.valueOf(s[0]);
        int goal = Integer.valueOf(s[1]);

        int maxScore = len/2 * (len - len/2);
        if (goal > maxScore) {
            System.out.println("-1");
            return;
        }

        StringBuilder sb = new StringBuilder();
        int myScore = 0;
        int aCnt = 0;
        for (int i = 1; i <= len; i++) {
            int obtainableScore = len - i - aCnt;
            if (obtainableScore <= 0) {
                sb.append("B");
                continue;
            }

            int nextScore = myScore + obtainableScore;
            if (nextScore <= goal) {
                sb.append("A");
                myScore = nextScore;
                aCnt++;
            } else {
                sb.append("B");
            }
        }

        System.out.println(sb);
    }
}
