import java.util.*;

class Solution {
    private static Map<Integer, List<Integer>> line;
    private static int[] visited;
    private static int max = 0;

    public static int solution(int n, int[][] edge) {
        int answer = 0;
        visited = new int[n+1];
        line = initLine(n);

        for (int i = 0; i < edge.length; i++) {
            int n1 = edge[i][0];
            int n2 = edge[i][1];
            line.get(n1).add(n2);
            line.get(n2).add(n1);
        }

        bfs(1);
        for (int i = 1; i < visited.length; i++) {
            if (max == visited[i]) {
                answer++;
            }
        }

        return answer;
    }

    private static Map<Integer, List<Integer>> initLine(int n) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }
        return map;
    }

    private static void bfs(int start) {
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(start);
        visited[start] = -1;

        while (!qu.isEmpty()) {
            int now = qu.poll();
            int nextStep;
            if (now == 1) {
                nextStep = 1;
            }
            else {
                nextStep = visited[now] + 1;
            }

            List<Integer> lines = line.get(now);
            for (int line : lines) {
                if (visited[line] != 0 && visited[line] <= nextStep) {
                    continue;
                }

                qu.offer(line);
                max = Math.max(max, nextStep);
                visited[line] = nextStep;
            }
        }
    }
}