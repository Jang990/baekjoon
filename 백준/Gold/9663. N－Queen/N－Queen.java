import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N;
    private static int answer;
    private static int[] queen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.valueOf(br.readLine());
        br.close();

        queen = new int[N];
        Rec(0);
        System.out.println(answer);
    }

    private static void Rec(int depth) {
        if (depth == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            queen[depth] = i;
            if (isOk(depth)) {
                Rec(depth + 1);
            }
        }
    }

    private static boolean isOk(int depth) {
        for (int i = 0; i < depth; i++) {
            if (queen[depth] == queen[i]) {
                return false;
            }
            if (Math.abs(depth - i) == Math.abs(queen[depth] - queen[i])) {
                return false;
            }
        }

        return true;
    }


}
