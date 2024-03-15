import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int result = 0;
    private static boolean[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int removedNode = Integer.parseInt(br.readLine());
        br.close();

        int root = -1;
        graph = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
                continue;
            }
            if(parent == removedNode || i == removedNode)
                continue;
            graph[parent][i] = true;
        }

        if (root == removedNode) {
            System.out.println(0);
            return;
        }

        dfs(root);
        System.out.println(result);
    }

    private static void dfs(int node) {
        if (isLeaf(node)) {
            result++;
            return;
        }

        for (int i = 0; i < graph[0].length; i++) {
            if(graph[node][i])
                dfs(i);
        }
    }

    private static boolean isLeaf(int node) {
        for (int i = 0; i < graph[0].length; i++) {
            if(graph[node][i])
                return false;
        }
        return true;
    }
}
