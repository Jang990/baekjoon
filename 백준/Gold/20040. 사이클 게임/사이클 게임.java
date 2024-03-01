import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int result = 0;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            if (!union(n1, n2)) {
                result = i;
                break;
            }
        }
        br.close();

        System.out.println(result);
    }

    private static int find(int n) {
        if(parent[n] == n)
            return n;
        return parent[n] = find(parent[n]);
    }

    private static boolean union(int n1, int n2) {
        int n1Parent = find(n1);
        int n2Parent = find(n2);
        if(n1Parent == n2Parent)
            return false;

        if(n1Parent < n2Parent)
            parent[n2Parent] = n1Parent;
        else
            parent[n1Parent] = n2Parent;
        return true;
    }
}
