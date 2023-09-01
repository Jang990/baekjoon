import java.util.ArrayList;
import java.util.List;

class Solution {
    private static int[] state;
    private static int[][] graphs;
    private static boolean[] visited;
    private static int answer = 0;

    public static int solution(int[] info, int[][] edges) {
        state = info;
        graphs = new int[info.length][info.length];
        visited = new boolean[info.length];
        for (int i = 0; i < edges.length; i++) {
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            graphs[n1][n2] = 1;
            graphs[n2][n1] = 1;
        }

        // 현재 양의 값과, 늑대의 값, 인접 노드를 준다.
        List<Integer> list = getNearNode(0);
        visited[0] = true;
        dfs(list, 1, 0);

        return answer;
    }

    private static List<Integer> getNearNode(int now) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < graphs[now].length; i++) {
            if (graphs[now][i] == 1 && !visited[i]) {
                list.add(i);
            }
        }
        return list;
    }

    private static void dfs(List<Integer> node, int sheep, int wolf) {
        answer = Math.max(answer, sheep);

        for (Integer next : node) {
            if (sheep == wolf + 1 && state[next] == 1) {
                continue;
            }

            List<Integer> nextNearNode = getNearNode(next);
            nextNearNode.addAll(node);
            nextNearNode.remove(next);
            visited[next] = true;
            if (state[next] == 1) {
                dfs(nextNearNode, sheep, wolf+1);
            }
            else {
                dfs(nextNearNode, sheep + 1, wolf);
            }
            visited[next] = false;
        }
    }
}