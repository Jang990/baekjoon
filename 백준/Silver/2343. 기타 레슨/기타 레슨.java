import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int lessonCnt = Integer.valueOf(st.nextToken());
        int cdCnt = Integer.valueOf(st.nextToken());
        int[] lesson = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();
        br.close();

        int left = Arrays.stream(lesson).max().getAsInt(), right = Integer.MAX_VALUE-left;
        while (left < right) {
            int mid = (right + left) / 2;
            int nowCdCnt = 1;
            int sum = 0;
            for (int i = 0; i < lessonCnt; i++) {
                sum += lesson[i];
                if (sum > mid) {
                    sum = lesson[i];
                    nowCdCnt++;
                }
            }

            if (nowCdCnt > cdCnt)
                left = mid+1;
            else
                right = mid;
        }

        System.out.println(left);
    }
}
