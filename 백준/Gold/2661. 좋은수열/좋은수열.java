import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] arr;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.valueOf(br.readLine());
        br.close();

        arr = new int[N];
        arr[0] = 1;
        rec(1);
    }

    private static void rec(int depth) {
        if (depth == N) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                sb.append(arr[i]);
            }
            System.out.println(sb);
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            arr[depth] = i;
            if (isOkSeq(depth+1)) {
                rec(depth+1);
            }
            arr[depth] = 0;
        }
    }

    private static boolean isOkSeq(int depth) {
        int len = depth / 2;
        for (int i = 1; i <= len; i++) {
            boolean isWrongSeq = true;
            for (int j = 0; j < i; j++) {
                if (arr[depth -1 - i - j] != arr[depth -1 - j]) {
                    isWrongSeq = false;
                    break;
                }
            }
            if (isWrongSeq) {
                return false;
            }
        }

        return true;
    }
}
