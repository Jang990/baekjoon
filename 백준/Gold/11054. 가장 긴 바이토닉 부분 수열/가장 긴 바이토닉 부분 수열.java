import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int[] arr;
    private static int[] lis;
    private static int[] lds;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();

        lis = new int[n];
        lds = new int[n];

        for (int i = 0; i < n; i++) {
            LIS(i);
            LDS(i);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, lis[i] + lds[i]);
        }

        System.out.println(result + 1);
    }

    private static int LDS(int idx) {
        if (lds[idx] == 0) {
            for (int i = idx; i < arr.length; i++) {
                if (arr[idx] > arr[i]) {
                    lds[idx] = Math.max(lds[idx], LDS(i) + 1);
                }
            }
        }

        return lds[idx];
    }

    private static int LIS(int idx) {
        if (lis[idx] == 0) {
            for (int i = idx; i >= 0; i--) {
                if (arr[idx] > arr[i]) {
                    lis[idx] = Math.max(lis[idx], LIS(i) + 1);
                }
            }
        }

        return lis[idx];
    }
}
