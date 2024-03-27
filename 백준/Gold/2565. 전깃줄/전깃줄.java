import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        LinkedList<Line> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            int n1 = Integer.parseInt(line[0]);
            int n2 = Integer.parseInt(line[1]);
            list.add(new Line(n1, n2));
        }
        br.close();

        list.sort(Comparator.comparing(Line::getStart));
        int[] arr = list.stream().map(Line::getEnd)
                .mapToInt(Integer::valueOf).toArray();
        int[] dp = new int[arr.length];
        dp[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[j] == 0 || arr[i] <= dp[j]) {
                    dp[j] = arr[i];
                    break;
                }
            }
        }

        System.out.println(Arrays.stream(dp).filter(num -> num == 0).count());
    }

    static class Line {
        final int start;
        final int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}
