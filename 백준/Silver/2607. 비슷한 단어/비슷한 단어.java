import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String base = br.readLine();
        int[] baseArr = toArr(base);

        int similarCnt = 0;
        for (int i = 1; i < n; i++) {
            String word = br.readLine();
            int[] wordArr = toArr(word);
            if(isSimilar(baseArr, wordArr))
                similarCnt++;
        }
        br.close();

        System.out.println(similarCnt);
    }

    private static boolean isSimilar(int[] baseArr, int[] wordArr) {
        int moreCnt = 0;
        int lessCnt = 0;
        for (int i = 0; i < 26; i++) {
            if(baseArr[i] == wordArr[i])
                continue;
            if(baseArr[i] > wordArr[i])
                lessCnt += (baseArr[i] - wordArr[i]);
            else
                moreCnt += (wordArr[i] - baseArr[i]);
        }

        boolean isSame = moreCnt == 0 && lessCnt == 0;
        boolean isCanChanged = moreCnt == 1 && lessCnt == 1;
        boolean isCanRemoved = moreCnt == 1 && lessCnt == 0;
        boolean isCanAdded = moreCnt == 0 && lessCnt == 1;
        return isSame || isCanChanged || isCanRemoved || isCanAdded;
    }

    private static int[] toArr(String base) {
        int[] baseArr = new int[26];
        for (int i = 0; i < base.length(); i++) {
            int idx = base.charAt(i) - 'A';
            baseArr[idx]++;
        }
        return baseArr;
    }
}
