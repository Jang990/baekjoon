import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    static int donut = 0, stick = 0, eight = 0;
    static int[] parent = new int[1_000_001];
    static int[] enter = new int[1_000_001];
    static List<Integer>[] graph = new List[1_000_001];
    public static int[] solution(int[][] edges) {
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int start = edges[i][0];
            int end = edges[i][1];
            graph[start].add(end);
            enter[end]++;
        }

        int startNode = getStartNode();
        for (int node : graph[startNode]) {
            check(node);
        }

        return new int[] {startNode, donut, stick, eight};
    }

    private static void check(int node) {
        if (graph[node].size() == 0) {
            stick++;
            return;
        }

        int nodeCnt = 0, edgeCnt = 0;
        boolean cycleFlag = false;
        Queue<Integer> qu = new LinkedList<>();
        qu.offer(node);
        while (!qu.isEmpty()) {
            int now = qu.poll();
            nodeCnt++;
            edgeCnt += graph[now].size();
            for (int next : graph[now]) {
                if (isCycle(now, next)) {
                    cycleFlag = true;
                    continue;
                }

                union(now, next);
                qu.offer(next);
            }
        }

        if(nodeCnt == edgeCnt)
            donut++;
        else if(cycleFlag)
            eight++;
        else
            stick++;
    }

    private static boolean isCycle(int n1, int n2) {
        return find(n1) == find(n2);
    }

    private static int find(int node) {
        if(parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }

    private static void union(int n1, int n2) {
        int parent1 = find(n1);
        int parent2 = find(n2);
        if(parent1 > parent2)
            parent[parent1] = parent2;
        else
            parent[parent2] = parent1;
    }

    private static int getStartNode() {
        for (int i = 1; i < enter.length; i++) {
            if(enter[i] == 0 && graph[i].size() > 1)
                return i;
        }
        throw new IllegalStateException();
    }
}