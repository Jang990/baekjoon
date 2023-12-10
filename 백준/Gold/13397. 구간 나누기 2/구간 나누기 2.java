import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int arrSize = Integer.parseInt(st.nextToken());
        int divCnt = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();

        int max = Arrays.stream(arr).max().getAsInt();


        int left = 0, right = max+1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (isOk(arr, mid, divCnt))
                left = mid + 1;
            else
                right = mid;
        }
        System.out.println(right);
    }

    private static boolean isOk(int[] arr, int mid, int divCnt) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int nowDivCnt = 1;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(arr[i], min);
            max = Math.max(arr[i], max);
            if (max - min > mid) {
                nowDivCnt++;
                min = Integer.MAX_VALUE;
                max = Integer.MIN_VALUE;
                i--;
            }
        }

        return nowDivCnt > divCnt;
    }
}
