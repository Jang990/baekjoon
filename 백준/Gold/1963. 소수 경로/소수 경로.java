import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static boolean[] isPrime = new boolean[10001];
    public static void main(String[] args) throws IOException {
        for (int i = 2; i < isPrime.length; i++) {
            isPrime[i] = isPrime(i);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (testcase-- > 0) {
            String[] arr = br.readLine().split(" ");
            int base = Integer.parseInt(arr[0]);
            int target = Integer.parseInt(arr[1]);
            int result = bfs(base, target);
            if(result == -1)
                sb.append("Impossible");
            else
                sb.append(result);
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static int bfs(int base, int target) {
        if (base == target)
            return 0;

        Queue<Integer> qu = new LinkedList<>();
        int[] visited = new int[10001];
        qu.offer(base);
        visited[base] = 1;

        while (!qu.isEmpty()) {
            int now = qu.poll();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    if (i == 0 && j == 0)
                        continue;

                    int next = convert(now, i, j);
                    if(!isPrime[next] || (visited[next] != 0 && visited[next] <= visited[now]))
                        continue;

                    if (next == target)
                        return visited[now];

                    qu.offer(next);
                    visited[next] = visited[now] + 1;
                }
            }
        }

        return -1;
    }

    private static int convert(int base, int digits, int changed) {
        int[] num = {
                base / 1000,
                (base % 1000) / 100,
                (base % 100) / 10,
                base % 10
        };
        num[digits] = changed;

        int result = 0;
        for (int i = 0; i < 4; i++) {
           result += num[i] * Math.pow(10, 3 - i);
        }
        return result;
    }

    private static boolean isPrime(int num) {
        int limit = (int) Math.sqrt(num);
        for (int i = 2; i <= limit; i++) {
            if(num % i == 0)
                return false;
        }
        return true;
    }
}
