import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static char[][] graph;
    private static int result = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        graph = new char[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                graph[i][j] = line.charAt(j);
            }
        }

        br.close();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                swapBottomCandy(j, i);
                swapRightCandy(j, i);
            }
        }

        System.out.println(result);
    }

    private static void swapBottomCandy(int x, int y) {
        char[][] cloneGraph = getCloneGraph();
        if (cloneGraph.length <= y + 1) {
            return;
        }

        char temp = cloneGraph[y][x];
        cloneGraph[y][x] = cloneGraph[y + 1][x];
        cloneGraph[y + 1][x] = temp;

        result = Math.max(result, getMaxCandy(cloneGraph));
    }

    private static void swapRightCandy(int x, int y) {
        char[][] cloneGraph = getCloneGraph();
        if (cloneGraph.length <= x + 1) {
            return;
        }

        char temp = cloneGraph[y][x];
        cloneGraph[y][x] = cloneGraph[y][x + 1];
        cloneGraph[y][x + 1] = temp;

        result = Math.max(result, getMaxCandy(cloneGraph));
    }

    private static char[][] getCloneGraph() {
        char[][] clone = new char[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            clone[i] = Arrays.copyOf(graph[i], graph.length);
        }
        return clone;
    }

    private static int getMaxCandy(char[][] cloneGraph) {
        return Math.max(getRowMaxCandy(cloneGraph), getColumnMaxCandy(cloneGraph));
    }

    private static int getRowMaxCandy(char[][] cloneGraph) {
        int max = 0;
        for (int i = 0; i < cloneGraph.length; i++) {
            int cntNow = 0;
            char prev = 0;
            for (int j = 0; j < cloneGraph.length; j++) {
                char now = cloneGraph[i][j];
                if (prev != now) {
                    max = Math.max(max, cntNow);
                    prev = now;
                    cntNow = 1;
                    continue;
                }

                cntNow++;
            }

            max = Math.max(max, cntNow);
        }

        return max;
    }

    private static int getColumnMaxCandy(char[][] cloneGraph) {
        int max = 0;
        for (int i = 0; i < cloneGraph.length; i++) {
            int cntNow = 0;
            char prev = 0;
            for (int j = 0; j < cloneGraph.length; j++) {
                char now = cloneGraph[j][i];
                if (prev != now) {
                    max = Math.max(max, cntNow);
                    prev = now;
                    cntNow = 1;
                    continue;
                }

                cntNow++;
            }

            max = Math.max(max, cntNow);
        }

        return max;
    }
}
