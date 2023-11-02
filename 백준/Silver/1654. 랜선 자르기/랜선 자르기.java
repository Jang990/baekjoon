import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.valueOf(st.nextToken());
        int n = Integer.valueOf(st.nextToken());
        int[] lines = new int[k];

        long min = 1, max = 1;
        for (int i = 0; i < k; i++) {
            int line = Integer.valueOf(br.readLine());
            max = Math.max(line, max);
            lines[i] = line;
        }
        br.close();

        max++;
        while (min < max) {
            long mid = min + (max - min) / 2;
            int cnt = getCount(lines, mid);

            if(cnt < n)
                max = mid;
            else
                min = mid+1;

        }

        System.out.println(max-1);
    }

    private static int getCount(int[] lines, long length) {
        int cnt = 0;
        for (int line : lines) {
            cnt += (line / length);
        }
        return cnt;
    }
}
