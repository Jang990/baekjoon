import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        br.close();

        if(isCorrect(line))
            System.out.println(1);
        else
            System.out.println(0);
//        ArrayPrinter.print(wordCnt);
    }

    private static boolean isCorrect(String line) {
        int expectedIdx = 0;
        int[] wordCnt = new int[4];
        for (int i = 0; i < line.length(); i++) {
            int idx = getIdx(line.charAt(i));

            if (expectedIdx == 0 && idx == 1 && wordCnt[0] != 0) {
                wordCnt[idx]++;
                expectedIdx = expectNext(wordCnt);
                continue;
            }

            if(expectedIdx != idx)
                return false;
            wordCnt[idx]++;
            if(isAllSame(wordCnt))
                clear(wordCnt);
            expectedIdx = expectNext(wordCnt);
        }

        return isAllSame(wordCnt);
    }

    private static void clear(int[] wordCnt) {
        wordCnt[0] = 0;
        wordCnt[1] = 0;
        wordCnt[2] = 0;
        wordCnt[3] = 0;
    }

    private static boolean isAllSame(int[] wordCnt) {
        return wordCnt[0] == wordCnt[1]
                && wordCnt[1] == wordCnt[2]
                && wordCnt[2] == wordCnt[3];
    }

    private static int expectNext(int[] wordCnt) {
        if(wordCnt[1] == 0 && wordCnt[2] == 0 && wordCnt[3] == 0)
            return 0;

        int len = wordCnt[0];
        for (int i = 1; i < 4; i++) {
            if(wordCnt[i] < len)
                return i;
        }
        return 0;
    }

    private static int getIdx(char c) {
        if(c == 'w')
            return 0;
        if(c == 'o')
            return 1;
        if(c == 'l')
            return 2;
        return 3;
    }
}
