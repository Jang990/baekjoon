import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static boolean[] afterBulb;
    private static boolean[] firstSwitchOff;
    private static boolean[] firstSwitchOn;
    static int firstOffCnt = 0;
    static int firstOnCnt = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        String before = br.readLine();
        String after = br.readLine();
        br.close();
        firstSwitchOn = new boolean[N];
        firstSwitchOff = new boolean[N];
        afterBulb = new boolean[N];

        for (int i = 0; i < N; i++) {
            boolean beforeBulb = before.charAt(i) == '1';
            firstSwitchOff[i] = beforeBulb;
            firstSwitchOn[i] = beforeBulb;
            afterBulb[i] = after.charAt(i) == '1';
        }

        switchLogic(firstSwitchOn, 0);
        calc(firstSwitchOn, 1);
        calc(firstSwitchOff, 1);

        int result = -1;
        boolean sameFirstOff = isSame(firstSwitchOff);
        boolean sameFirstOn = isSame(firstSwitchOn);
        if (sameFirstOff && sameFirstOn) {
            result = Math.min(firstOffCnt, firstOnCnt);
        }
        else if(sameFirstOff) {
            result = firstOffCnt;
        } else if (sameFirstOn) {
            result = firstOnCnt;
        }

        System.out.println(result);
    }

    private static boolean isSame(boolean[] resultBulb) {
        for (int i = 0; i < afterBulb.length; i++) {
            if (afterBulb[i] != resultBulb[i]) {
                return false;
            }
        }
        return true;
    }

    private static void calc(boolean[] bulb, int idx) {
        if (idx == bulb.length) {
            return;
        }

        if (bulb[idx - 1] != afterBulb[idx - 1]) {
            switchLogic(bulb, idx);
        }
        calc(bulb,idx+1);
    }

    private static void switchLogic(boolean[] bulb, int idx) {
        if (bulb == firstSwitchOff) {
            firstOffCnt++;
        }
        else {
            firstOnCnt++;
        }

        if (idx != 0) {
            bulb[idx - 1] = !bulb[idx - 1];
        }

        bulb[idx] = !bulb[idx];

        if (idx != bulb.length - 1) {
            bulb[idx + 1] = !bulb[idx + 1];
        }
    }
}
