import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int total;
    static int min = Integer.MAX_VALUE;
    static int[] person;
    static boolean[] divResult;
    static List<Integer>[] graph;
    static int city;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        total = 0;
        city = Integer.parseInt(br.readLine());
        graph = new List[city +1];
        person = new int[city + 1];
        divResult = new boolean[city + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= city; i++) {
            graph[i] = new LinkedList<>();
            person[i] = Integer.parseInt(st.nextToken());
            total += person[i];
        }

        for (int i = 1; i <= city; i++) {
            st = new StringTokenizer(br.readLine());
            int edgeCount = Integer.parseInt(st.nextToken());
            for (int j = 0; j < edgeCount; j++) {
                int n = Integer.parseInt(st.nextToken());
                graph[i].add(n);
            }
        }
        br.close();

        rec(1);

        if(min == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(min);
    }

    private static void rec(int now) {
        if (now > city) {
            if (isConnected(true) && isConnected(false)) {
                min = Math.min(min, Math.abs(total - sum() * 2));
            }
            return;
        }

        divResult[now] = true;
        rec(now + 1);
        divResult[now] = false;

        rec(now + 1);
    }

    private static boolean isConnected(boolean target) {
        int start = 0;
        for (int i = 1; i < divResult.length; i++) {
            if (divResult[i] != target) continue;
            start = i;
        }

        if(start == 0)
            return false;

        Queue<Integer> qu = new LinkedList<>();
        boolean[] visited = new boolean[city + 1];
        qu.offer(start);
        visited[start] = true;

        while (!qu.isEmpty()) {
            int now = qu.poll();
            for (int next : graph[now]) {
                if(divResult[next] != target || visited[next])
                    continue;
                qu.offer(next);
                visited[next] = true;
            }
        }


        for (int i = 1; i < divResult.length; i++) {
            if(divResult[i] == target && !visited[i])
                return false;
        }

        return true;
    }

    private static int sum() {
        int sum = 0;
        for (int i = 0; i < divResult.length; i++) {
            if(divResult[i])
                sum += person[i];
        }
        return sum;
    }
}
