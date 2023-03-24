import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int house = Integer.valueOf(br.readLine());
        int[][] dp = new int[house][3];
        int[][] dirs = {{1,2}, {-1,1}, {-2,-1}};
        dp[0] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();

        for (int i = 1; i < house; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int num = Integer.valueOf(st.nextToken());
                dp[i][j] = Math.min(num + dp[ i-1 ][ j+dirs[j][0] ],
                        num + dp[ i-1 ][ j+dirs[j][1] ] );
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            min = Math.min(min, dp[house - 1][i]);
        }
        System.out.println(min);

        br.close();
    }
}
