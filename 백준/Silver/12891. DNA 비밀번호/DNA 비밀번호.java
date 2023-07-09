import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int A_IDX = 0;
    static final int C_IDX = 1;
    static final int G_IDX = 2;
    static final int T_IDX = 3;

    static final int TYPE_COUNT = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int dnaLength = Integer.parseInt(st.nextToken());
        int passwordLength = Integer.parseInt(st.nextToken());
        String dna = br.readLine();
        int[] dnaLimit = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();

        int result = 0;
        int[] dnaCnt = new int[TYPE_COUNT];
        for (int i = 0; i < passwordLength; i++) {
            dnaCnt[getDnaIdx(dna.charAt(i))]++;
        }

        if (isOk(dnaCnt, dnaLimit)) {
            result++;
        }

        for (int i = passwordLength; i < dnaLength; i++) {
            char deletedDna = dna.charAt(i - passwordLength);
            dnaCnt[getDnaIdx(deletedDna)]--;

            char addedDna = dna.charAt(i);
            dnaCnt[getDnaIdx(addedDna)]++;

            if (isOk(dnaCnt, dnaLimit)) {
                result++;
            }
        }

        System.out.println(result);
    }

    private static boolean isOk(int[] passwordDnaCnt, int[] dnaLimit) {
        for (int i = 0; i < TYPE_COUNT; i++) {
            if (dnaLimit[i] == 0) {
                continue;
            }

            if(dnaLimit[i] > passwordDnaCnt[i])
                return false;
        }

        return true;
    }

    private static int getDnaIdx(char c) {
        switch (c) {
            case 'A':
                return A_IDX;
            case 'C':
                return C_IDX;
            case 'G':
                return G_IDX;
            case 'T':
                return T_IDX;
        }

        return -1;
    }
}
