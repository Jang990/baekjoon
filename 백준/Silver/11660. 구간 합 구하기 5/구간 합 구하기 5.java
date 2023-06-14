import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N+1][N+1];

        for (int i = 1; i < graph.length; i++) {
            int[] line = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int sum = 0;
            for (int j = 1; j < graph[0].length; j++) {
                sum += line[j - 1];
                graph[i][j] = sum;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            int sum = 0;
            for (int j = y1; j <= y2; j++) {
                sum += graph[j][x2] - graph[j][x1 - 1];
            }
            sb.append(sum + "\n");
        }
        br.close();

        System.out.println(sb.toString());
    }
}
