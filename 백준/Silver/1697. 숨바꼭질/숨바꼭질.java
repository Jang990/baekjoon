import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] visited = new int[100_000+1];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int K = Integer.valueOf(st.nextToken());
        br.close();

        if (N == K) {
            System.out.println(0);
            return;
        }


        bfs(N, K);
        System.out.println(visited[K]);
    }

    private static void bfs(int N, int K) {
        Queue<Integer> qu = new LinkedList<>();
        move(qu, N);
        visited[N] = -1;

        while (!qu.isEmpty()) {
            int now = qu.poll();
            if (now == K) {
                return;
            }

            move(qu, now);
        }
    }

    private static void move(Queue<Integer> qu, int now) {
        int nextStep = visited[now] + 1;
        int next1 = now - 1;
        if (next1 >= 0) {
            if (nextStep < visited[next1] || visited[next1] == 0) {
                qu.offer(next1);
                visited[next1] = nextStep;
            }

        }

        int next2 = now + 1;
        if (next2 <= 100_000) {
            if (nextStep < visited[next2] || visited[next2] == 0) {
                qu.offer(next2);
                visited[next2] = nextStep;
            }

        }

        int next3 = now * 2;
        if (next3 <= 100_000) {
            if (nextStep < visited[next3] || visited[next3] == 0) {
                qu.offer(next3);
                visited[next3] = nextStep;
            }
        }
    }
}
