import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        initGraph();

        int[] score = new int[graph.length];
        int minScore = Integer.MAX_VALUE;
        for (int i = 1; i < score.length; i++) {
            score[i] = getScore(i);
            minScore = Math.min(score[i], minScore);
        }

        StringBuilder sb = new StringBuilder();
        List<Integer> ids = find(score, minScore);
        sb.append(minScore).append(" ").append(ids.size()).append("\n");
        ids.forEach(id -> sb.append(id).append(" "));

        System.out.println(sb);
    }

    private static List<Integer> find(int[] score, int minScore) {
        List<Integer> result = new LinkedList<>();
        for (int id = 1; id < score.length; id++) {
            if(score[id] == minScore)
                result.add(id);
        }
        return result;
    }

    private static int getScore(int start) {
        int result = Integer.MIN_VALUE;
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(start);

        int[] visited = new int[graph.length];
        visited[start] = 1;

        while (!qu.isEmpty()) {
            int now = qu.poll();
            for (int next : graph[now]) {
                int nextStep = visited[now] + 1;
                if(visited[next] != 0 && visited[next] <= nextStep)
                    continue;

                visited[next] = nextStep;
                qu.offer(next);
                result = Math.max(nextStep, result);
            }
        }

        return result - 1;
    }

    private static void initGraph() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        graph = new List[n + 1];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }

        while (true) {
            String[] args = br.readLine().split(" ");
            if(args[0].equals("-1") & args[1].equals("-1"))
                break;

            int n1 = Integer.parseInt(args[0]);
            int n2 = Integer.parseInt(args[1]);
            graph[n1].add(n2);
            graph[n2].add(n1);
        }

        br.close();
    }
}
