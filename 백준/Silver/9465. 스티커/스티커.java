import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (testcase-- > 0) {
            int lineCnt = Integer.parseInt(br.readLine());
            int[][] graph = initGraph(lineCnt, br);
            int[][] dp = new int[3][lineCnt]; // 위 선택, 아래 선택, 선택 x
            dp[0][0] = graph[0][0];
            dp[1][0] = graph[1][0];
            for (int i = 1; i < graph[0].length; i++) {
                dp[0][i] = graph[0][i] + Math.max(dp[1][i - 1], dp[2][i - 1]);
                dp[1][i] = graph[1][i] + Math.max(dp[0][i - 1], dp[2][i - 1]);
                dp[2][i] = Math.max(dp[0][i - 1], dp[1][i - 1]);
            }
            sb.append(Math.max(Math.max(dp[0][lineCnt - 1], dp[1][lineCnt - 1]), dp[2][lineCnt - 1])).append("\n");
        }
        br.close();
        System.out.println(sb);
    }

    private static int[][] initGraph(int lineCnt, BufferedReader br) throws IOException {
        int[][] graph = new int[2][lineCnt];
        graph[0] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        graph[1] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        return graph;
    }
}
