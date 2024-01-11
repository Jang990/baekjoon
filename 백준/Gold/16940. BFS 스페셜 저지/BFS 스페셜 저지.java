import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, Set<Integer>> map = new HashMap<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        /*
        핵심은 진짜 BFS처럼 돌리는 것이다.
        1 - 2 - 4
          - 3 - 5
        그래프가 위와 같을 때
        1 2 3 4 5 는 가능
        1 2 3 5 4 는 불가능 - 2를 먼저 방문해서 큐에 들어가 있기 때문
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        for (int i = 1; i < n; i++) {
            String[] arr = br.readLine().split(" ");
            int n1 = Integer.parseInt(arr[0]);
            int n2 = Integer.parseInt(arr[1]);
            Set<Integer> set1 = map.getOrDefault(n1, new HashSet<>());
            set1.add(n2);
            map.put(n1, set1);
            Set<Integer> set2 = map.getOrDefault(n2, new HashSet<>());

            set2.add(n1);
            map.put(n2, set2);
        }

        int[] result = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();

        if (result[0] != 1) {
            System.out.println(0);
            return;
        }

        int[] range = new int[n+1];
        range[1] = 1 + map.get(result[0]).size();
        visited[0] = true;
        visited[1] = true;
        for (int i = 1; i < result.length; i++) {
            range[result[i]] = range[result[i - 1]] + map.get(result[i]).size() - 1;
        }

        visit(result[0]);
        if (isOut(range[0], range[1], result)) {
            System.out.println(0);
            return;
        }

        for (int i = 1; i < result.length; i++) {
            visit(result[i]);
            if (isOut(range[result[i - 1]], range[result[i]], result)) {
                System.out.println(0);
                return;
            }
        }

        System.out.println(1);

    }

    private static boolean isOut(int start, int end, int[] result) {
        for (int i = start; i < end; i++) {
            if(!visited[result[i]])
                return true;
        }

        return false;
    }

    private static void visit(int now) {
        for (int n : map.get(now)) {
            visited[n] = true;
        }
    }
}
