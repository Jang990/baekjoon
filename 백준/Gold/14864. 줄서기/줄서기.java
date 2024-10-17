import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    private static int[] biggerCnt;
    private static int[] smallerCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);

        biggerCnt = initBiggerCnt(N);
        smallerCnt = initSmallerCnt(N);
        for (int i = 0; i < M; i++) {
            line = br.readLine().split(" ");
            int big = Integer.parseInt(line[0]) - 1;
            int small = Integer.parseInt(line[1]) - 1;

            big(big);
            small(small);
        }
        br.close();


        if (isWrong(biggerCnt)) {
            System.out.println(-1);
            return;
        }

        print(biggerCnt);
    }

    private static boolean isWrong(int[] biggerCnt) {
        boolean[] visited = new boolean[biggerCnt.length];
        for (int i : biggerCnt) {
            int idx = i - 1;
            if(idx < 0 || biggerCnt.length <= idx)
                return true;
            visited[idx] = true;
        }

        for (boolean b : visited) {
            if(!b)
                return true;
        }
        return false;
    }

    private static void print(int[] biggerCnt) {
        StringBuilder sb = new StringBuilder();
        for (int i : biggerCnt) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    private static void small(int small) {
        smallerCnt[small]++;
        biggerCnt[small]--;
    }

    private static void big(int big) {
        biggerCnt[big]++;
        smallerCnt[big]--;
    }

    private static int[] initSmallerCnt(int n) {
        return IntStream.range(0, n)
                .map(i -> n - i)
                .toArray();
    }

    private static int[] initBiggerCnt(int n) {
        return IntStream.range(0, n)
                .map(i -> i + 1)
                .toArray();
    }
}
