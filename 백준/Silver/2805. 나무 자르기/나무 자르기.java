import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());
        int[] trees = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();
        br.close();

        long min = 1, max = 1;
        for (int tree : trees) {
            max = Math.max(tree, max);
        }
        max++;

        while (min < max) {
            long mid = (min + max) / 2;
            long totalLength = getTotalLength(trees, mid);

            if (totalLength < m) {
                max = mid;
            } else {
                min = mid+1;
            }

        }

        System.out.println(max-1);
    }

    private static long getTotalLength(int[] trees, long length) {
        long total = 0;
        for (int tree : trees) {
            if(tree <= length)
                continue;

            total += (tree - length);
        }

        return total;
    }
}
