import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int C = Integer.parseInt(line[1]);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        Arrays.sort(arr);
        int left = 1;
        int right = arr[arr.length - 1] - arr[0] + 1;
        int mid = -1;
        while (left < right) {
            mid = (left + right) / 2;
            int cnt = calculate(mid, arr);
            if(cnt < C)
                right = mid;
            else
                left = mid + 1;
        }

        System.out.println(left - 1);
    }

    private static int calculate(int minLen, int[] arr) {
        int result = 1;
        int before = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int len = arr[i] - before;
            if(minLen > len)
                continue;
            before = arr[i];
            result++;
        }

        return result;
    }
}
