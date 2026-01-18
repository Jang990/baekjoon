import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int bottleCnt = Integer.parseInt(line[0]);
        int buddyCnt = Integer.parseInt(line[1]);

        int[] bottle = new int[bottleCnt];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < bottleCnt; i++) {
            bottle[i] = Integer.parseInt(br.readLine());
            max = Math.max(bottle[i], max);
        }
        br.close();

        long left = 0, right = max + 1;
        while (left < right) {
            long mid = (left + right) / 2;
            long currentBuddyCnt = calcCnt(bottle, mid);
            if(currentBuddyCnt >= buddyCnt)
                left = mid + 1;
            else
                right = mid;
        }

        System.out.println(right - 1);
    }

    private static long calcCnt(int[] bottle, long mid) {
        long result = 0;
        for (int i = 0; i < bottle.length; i++)
            result += (bottle[i] / mid);
        return result;
    }
}
