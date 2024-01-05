import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

    private static int goal;
    private static int[] visited = new int[100001];
    private static int min = Integer.MAX_VALUE;
    private static String str = "";


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int now = Integer.parseInt(arr[0]);
        goal = Integer.parseInt(arr[1]);
        br.close();

        bfs(now);
    }

    private static void bfs(int start) {
        Queue<Integer> qu = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        int[] log = new int[100001];
        visited[start] = true;
        log[start] = -1;
        qu.offer(start);

        while (!qu.isEmpty()) {
            int now = qu.poll();
            if (now == goal) {
                StringBuilder sb = new StringBuilder();
                Stack<Integer> st = getLogs(log, now);
                
                sb.append(st.size() - 1).append("\n");
                while (!st.isEmpty()) {
                    sb.append(st.pop()).append(" ");
                }
                System.out.println(sb);
                return;
            }
            if (now > 0) {
                if (!visited[now - 1]) {
                    visited[now-1] = true;
                    log[now - 1] = now;
                    qu.offer(now - 1);
                }
                if (now * 2 <= 100000 && !visited[now * 2]) {
                    visited[now * 2] = true;
                    log[now * 2] = now;
                    qu.offer(now * 2);
                }
            }

            if (now < 100000 && !visited[now + 1]) {
                visited[now + 1] = true;
                log[now + 1] = now;
                qu.offer(now + 1);
            }
        }
    }

    private static Stack<Integer> getLogs(int[] log, int now) {
        Stack<Integer> st = new Stack<>();
        st.push(now);
        int i = now;
        while (log[i] != -1) {
            i = log[i];
            st.push(i);
        }
        return st;
    }
}
