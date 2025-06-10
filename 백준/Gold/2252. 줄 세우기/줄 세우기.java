import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int[] in;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = readLine(br);
        int N = input[0];
        int M = input[1];
        graph = new List[N + 1];
        in = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++)
            graph[i] = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            input = readLine(br);
            graph[input[0]].add(input[1]);
            in[input[1]]++;
        }

        br.close();

        System.out.println(search());
    }

    private static String search() {
        StringBuilder sb = new StringBuilder();

        Queue<Integer> qu = findZero();
        while (!qu.isEmpty()) {
            int zeroNode = qu.poll();
            for (Integer nextNode : graph[zeroNode]) {
                in[nextNode]--;
                if(in[nextNode] == 0)
                    qu.add(nextNode);
            }
            sb.append(zeroNode).append(" ");
        }

        return sb.toString();
    }

    private static LinkedList<Integer> findZero() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i < in.length; i++) {
            if(in[i] == 0 && !visited[i])
                list.add(i);
        }
        return list;
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
