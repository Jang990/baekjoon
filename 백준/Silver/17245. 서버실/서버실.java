import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < n; j++) {
                sum += graph[i][j];
            }
        }
        br.close();

        long goal = (long) Math.ceil((double) sum / 2);
        int left = 0, right = 5_000_000;
        while (left < right) {
            int mid = (left + right) / 2;
            long current = calcRunningServer(mid);
            if(current >= goal)
                right = mid;
            else
                left = mid + 1;
        }
        /*
2
2 2
2 2
답: 1
8 -> 4개 이상 켜져야함

2
2 2
2 3
답: 2
8 + 1 -> 5개 이상 켜져야함
*/

        System.out.println(right);
    }

    private static long calcRunningServer(int minute) {
        long runningServers = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if(graph[i][j] >= minute)
                    runningServers += minute;
                else
                    runningServers += graph[i][j];
            }
        }

        return runningServers;
    }
}
