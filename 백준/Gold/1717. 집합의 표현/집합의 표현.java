import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[] nodes;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        nodes = new int[n+1];
        for (int i = 0; i <= n; i++) {
            nodes[i] = i;
        }

        for (int i = 0; i < m; i++) {
            line = br.readLine().split(" ");
            int code = Integer.parseInt(line[0]);
            int n1 = Integer.parseInt(line[1]);
            int n2 = Integer.parseInt(line[2]);
            if (code == 0) {
                union(n1, n2);
            } else {
                sb.append(isUnion(n1, n2));
            }
        }
        br.close();

        System.out.println(sb);
    }

    private static String isUnion(int node1, int node2) {
        int n1Parent = getParent(node1);
        int n2Parent = getParent(node2);

        if (n1Parent == n2Parent)
            return "yes\n";
        else
            return "no\n";
    }

    private static void union(int node1, int node2) {
        int n1Parent = getParent(node1);
        int n2Parent = getParent(node2);
        if(n1Parent == n2Parent) return;

        if(n1Parent > n2Parent)
            nodes[n1Parent] = n2Parent;
        else
            nodes[n2Parent] = n1Parent;
    }

    private static int getParent(int node) {
        if(nodes[node] == node) return node;
        return nodes[node] = getParent(nodes[node]);
    }

}
