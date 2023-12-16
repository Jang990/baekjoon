import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        int[][] maze = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                maze[i][j] += Math.max(Math.max(maze[i-1][j], maze[i][j-1]), maze[i-1][j-1]);
            }
        }

        System.out.println(maze[N][M]);
    }
}
