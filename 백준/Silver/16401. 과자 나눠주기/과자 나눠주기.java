import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int childCnt = Integer.parseInt(st.nextToken());
        int snackCnt = Integer.parseInt(st.nextToken());

        int[] snacks = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).sorted().toArray();
        br.close();

        int left = 0, right = snacks[snackCnt-1] + 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (left == mid)
                right--;
            else if(isOk(snacks, mid, childCnt))
                left = mid;
            else
                right = mid;
        }

        System.out.println(left);
    }

    private static boolean isOk(int[] snacks, int mid, int childCnt) {
        for (int snack : snacks) {
            childCnt -= (snack / mid);
        }
        return childCnt <= 0;
    }
}
