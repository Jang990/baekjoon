import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        List<Answer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Answer(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken())));
        }
        br.close();

        int result = 0;
        for (int i = 100; i < 1000; i++) {
            if (isAllOk(i, list)) {
                result++;
            }
        }

        System.out.println(result);
    }

    private static boolean isAllOk(int target, List<Answer> list) {
        List<Integer> targetInfo = new ArrayList<>();
        targetInfo.add(target/100);
        targetInfo.add((target % 100) / 10);
        targetInfo.add(target % 10);

        if (targetInfo.contains(0)) {
            return false;
        }

        if (targetInfo.get(0) == targetInfo.get(1)
                || targetInfo.get(0) == targetInfo.get(2)
                || targetInfo.get(1) == targetInfo.get(2)) {
            return false;
        }

        for (Answer answer : list) {

            if (!answer.isOk(targetInfo)) {
                return false;
            }
        }

        return true;
    }

    private static class Answer {
        int n1;
        int n2;
        int n3;
        int strike;
        int ball;

        public Answer(int instance, int strike, int ball) {
            n1 = instance/100;
            n2 = (instance % 100) / 10;
            n3 = instance % 10;
            this.strike = strike;
            this.ball = ball;
        }

        public boolean isOk(List<Integer> targetInfo) {
            if (calcStrike(targetInfo) == this.strike && calcBall(targetInfo) == this.ball) {
                return true;
            }

            return false;
        }

        private int calcBall(List<Integer> targetInfo) {
            int targetBall = 0;

            if (n1 != targetInfo.get(0) && targetInfo.contains(n1)) {
                targetBall++;
            }
            if (n2 != targetInfo.get(1) && targetInfo.contains(n2)) {
                targetBall++;
            }
            if (n3 != targetInfo.get(2) && targetInfo.contains(n3)) {
                targetBall++;
            }

            return targetBall;
        }

        private int calcStrike(List<Integer> targetInfo) {
            int targetStrike = 0;


            if (n1 == targetInfo.get(0)) {
                targetStrike++;
            }
            if (n2 == targetInfo.get(1)) {
                targetStrike++;
            }
            if (n3 == targetInfo.get(2)) {
                targetStrike++;
            }

            return targetStrike;
        }
    }
}
