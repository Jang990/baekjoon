import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int C = Integer.valueOf(st.nextToken());
        int[] arr = new int[N];
        arr[0] = Integer.valueOf(br.readLine());
        for (int i = 1; i < N; i++) {
            arr[i] = Integer.valueOf(br.readLine());
        }
        br.close();

        Arrays.sort(arr);
        int left = 1, right = arr[N-1] - arr[0] + 1;
        for (int i = 1; i < N; i++) {
            left = Math.min(left, arr[i] - arr[i - 1]);
        }

        while (left < right) {
            int mid = (left + right) / 2;
            int cnt = getInstalledHouseCount(arr, mid);
            if(cnt < C)
                right = mid;
            else
                left = mid + 1;
        }

        System.out.println(left - 1);
    }

    private static int getInstalledHouseCount(int[] arr, int shortestDistance) {
        int cnt = 1;
        int lastInstalledLocation = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int distance = arr[i] - lastInstalledLocation;
            if (distance >= shortestDistance) {
                cnt++;
                lastInstalledLocation = arr[i];
            }
        }
        return cnt;
    }
}
