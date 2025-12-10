import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = readLine(br);
        int userCnt = arr[0];
        int edgeCnt = arr[1];
        graph = new List[userCnt + 1];
        for (int i = 0; i <= userCnt; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < edgeCnt; i++) {
            arr = readLine(br);
            graph[arr[0]].add(arr[1]);
            graph[arr[1]].add(arr[0]);
        }

        br.close();

        int[] kevinCnt = new int[graph.length];
        for (int i = 1; i < graph.length; i++) {
            kevinCnt[i] = search(i);
        }

        int min = Integer.MAX_VALUE;
        int result = 0;
        for (int userId = 1; userId < kevinCnt.length; userId++) {
            if(min <= kevinCnt[userId])
                continue;
            min = kevinCnt[userId];
            result = userId;
        }
        System.out.println(result);
    }

    private static int search(int id) {
        int[] steps = new int[graph.length];
        steps[id] = 1;
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(id);

        while (!qu.isEmpty()) {
            int current = qu.poll();
            for (int nextUserId : graph[current]) {
                if(steps[nextUserId] != 0 && steps[nextUserId] <= steps[current] + 1)
                    continue;
                qu.offer(nextUserId);
                steps[nextUserId] = steps[current] + 1;
            }
        }

        int result = 0;
        for (int step : steps) {
            if(step == 0)
                continue;
            result += (step - 1);
        }
        return result;
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
