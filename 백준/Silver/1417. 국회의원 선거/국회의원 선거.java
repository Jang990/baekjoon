import java.io.*;

public class Main {
    static int max = Integer.MIN_VALUE;
    private static int[] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        score = new int[n];
        for (int i = 0; i < n; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        int result = 0;
        max = getMax();
        while (score[0] != max || hasDuplicatedMaxScore()) {
            manipulate();
            max = getMax();
            result++;
        }

        System.out.println(result);
    }

    private static int count(int max) {
        int result = 0;
        for (int i = 0; i < score.length; i++) {
            if(score[i] == max)
                result++;
        }
        return result;
    }

    private static boolean hasDuplicatedMaxScore() {
        return count(max) > 1;
    }

    private static int getMax() {
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < score.length; i++) {
            result = Math.max(result, score[i]);
        }
        return result;
    }

    private static void manipulate() {
        for (int i = 1; i < score.length; i++) {
            if(max != score[i])
                continue;
            score[i]--;
            break;
        }
        score[0]++;
    }
}
