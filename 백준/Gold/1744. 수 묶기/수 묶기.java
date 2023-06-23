import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        Arrays.sort(arr);

        int sum = 0;
        /* - ~ 0 */
        /* 1 ~ + */
        int minusAndZeroCnt = getMinusAndZeroCnt(arr);
        int plusCnt = arr.length - minusAndZeroCnt;

        for (int i = 0; i < minusAndZeroCnt; i++) {
            if (i + 1 >= minusAndZeroCnt) {
                break;
            }

            int now = arr[i];
            int next = arr[i + 1];
            sum += now * next;
            i++;
        }

        if (minusAndZeroCnt % 2 == 1) {
            sum += arr[minusAndZeroCnt - 1];
        }

        for (int i = arr.length-1; i >= minusAndZeroCnt; i--) {
            if (i - 1 < minusAndZeroCnt) {
                break;
            }

            int now = arr[i];
            int next = arr[i - 1];

            if (next == 1) {
                sum += now + next;
            }
            else {
                sum += now * next;
            }

            i--;
        }

        if (plusCnt % 2 == 1) {
            sum += arr[minusAndZeroCnt];
        }

        System.out.println(sum);
    }

    private static int getMinusAndZeroCnt(int[] arr) {
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0)
                break;
            cnt++;
        }
        return cnt;
    }
}
