import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int playCnt;
    private static int childCnt;
    private static int[] playTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] cond = br.readLine().split(" ");
        childCnt = Integer.parseInt(cond[0]);
        playCnt = Integer.parseInt(cond[1]);
        playTime = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();
        br.close();

        if (childCnt <= playCnt) {
            System.out.println(childCnt);
            return;
        }

        childCnt -= playCnt;
        long left = 1, right = Long.valueOf(Arrays.stream(playTime).min().getAsInt()) * childCnt;
        while (left < right) {
            long mid = (left + right) / 2;
            if(isOk(mid))
                right = mid;
            else
                left = mid + 1;
        }

        long skip = getPlayingChild(left) - childCnt;
        for (int i = playTime.length-1; i >= 0; i--) {
            if(left % playTime[i] != 0)
                continue;

            if (skip != 0) {
                skip--;
                continue;
            }

            System.out.println(i+1);
            break;
        }
    }

    private static long getPlayingChild(long left) {
        long sum = 0;
        for (int time : playTime) {
            sum += left / time;
        }
        return sum;
    }

    private static boolean isOk(long mid) {
        long playChildCnt = 0;
        for (int i = 0; i < playTime.length; i++) {
            playChildCnt += (mid / playTime[i]);
        }

        return childCnt <= playChildCnt;
    }
}
