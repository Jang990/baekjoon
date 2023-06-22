import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();

        int[] cost = new int[N - 1];
        for (int i = 0; i < arr.length-1; i++) {
            cost[i] = arr[i+1] - arr[i];
        }

        Arrays.sort(cost);
        int result = 0;
        for (int i = 0; i < cost.length - K + 1; i++) {
            result += cost[i];
        }

        System.out.println(result);
    }
}
