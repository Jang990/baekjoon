import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    private static int[] arr;
    private static boolean[][] dp;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.valueOf(br.readLine());
        arr = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.valueOf(st.nextToken());
        }

        initDp();

        int m = Integer.valueOf(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String cond = br.readLine();
            int start = Integer.valueOf(cond.split(" ")[0]);
            int end = Integer.valueOf(cond.split(" ")[1]);
            int result = dp[end - start + 1][start] ? 1 : 0;
            sb.append(result).append("\n");
        }
        br.close();

        System.out.println(sb);
    }

    private static void initDp() {
        dp = new boolean[n +1][n +1];
        for (int i = 1; i <= n; i++) {
            dp[0][i] = true;
        }
        for (int i = 1; i <= n; i++) {
            dp[1][i] = true;
        }

        // dp[길이가 x일 때][y부터 시작하는게] 팰랜드롬이니?
        for (int len = 2; len <= n; len++) { // 길이
            // 처음과 끝이 같고 [처음-1]에서 [끝-1]이 팰렌드롬이라면 팰렌드롬
            for (int start = 1; start <= n-len+1; start++) { // 시작점
                if(arr[start] == arr[start+len-1] && dp[len-2][start+1])
                    dp[len][start] = true;
                else
                    dp[len][start] = false;
            }

        }
    }
}
