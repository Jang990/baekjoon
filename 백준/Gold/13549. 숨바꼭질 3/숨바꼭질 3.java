import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int MAX_SIZE = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int soobin = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());
        br.close();

        System.out.println(bfs(soobin, goal));
    }

    private static int bfs(int soobin, int goal) {
        Queue<Integer> qu = new LinkedList<>();
        int[] visited = new int[MAX_SIZE + 1];
        int[] dir = {1, -1};
        visited[soobin] = 1;
        qu.offer(soobin);

        while (!qu.isEmpty()) {
            int now = qu.poll();
            if(now == goal)
                break;

            if (!outOfBound(now * 2)
                    && (visited[now*2] == 0 || visited[now * 2] > visited[now])) {
                visited[now * 2] = visited[now];
                qu.offer(now * 2);
            }

            for (int i = 0; i < 2; i++) {
                int next = now + dir[i];
                if(outOfBound(next) || (visited[next] != 0 && visited[next] <= visited[now] + 1))
                    continue;
                visited[next] = visited[now] + 1;
                qu.offer(next);
            }
        }

         return visited[goal] - 1;
    }

    private static boolean outOfBound(int next) {
        return 0 > next || next > MAX_SIZE;
    }
}
