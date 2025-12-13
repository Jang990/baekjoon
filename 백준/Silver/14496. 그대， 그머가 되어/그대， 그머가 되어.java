import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    private static int start;
    private static int end;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = readLine(br);
        start = arr[0];
        end = arr[1];

        arr = readLine(br);
        int charCnt = arr[0];
        int convertCnt = arr[1];

        graph = new List[charCnt + 1];
        for (int i = 0; i < graph.length; i++)
            graph[i] = new LinkedList<>();

        while (convertCnt-- > 0) {
            arr = readLine(br);
            graph[arr[0]].add(arr[1]);
            graph[arr[1]].add(arr[0]);
        }

        br.close();

        if(start == end)
            System.out.println(0);
        else
            System.out.println(search());
    }

    private static int search() {
        Queue<Integer> qu = new LinkedList<>();
        int[] visited = new int[graph.length];

        qu.offer(start);
        visited[start] = 1;


        while (!qu.isEmpty()) {
            int current = qu.poll();
            for (int next : graph[current]) {
                int nextStep = visited[current] + 1;
                if(visited[next] != 0 && visited[next] <= nextStep)
                    continue;
                qu.offer(next);
                visited[next] = nextStep;
            }
        }

        if(visited[end] == 0)
            return -1;
        return visited[end] - 1;
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
