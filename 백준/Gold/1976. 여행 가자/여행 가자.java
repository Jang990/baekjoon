import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int planCnt = Integer.parseInt(br.readLine());
        initParent(n);
        for (int i = 0; i < n; i++) {
            String[] arr = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                if(arr[j].equals("1")) {
                    union(i + 1, j + 1);
                }
            }
        }
        int[] plan = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();

        if(isOk(plan))
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    private static boolean isOk(int[] plan) {
        int startParent = parent[plan[0]];
        for (int i = 1; i < plan.length; i++) {
            if(startParent != find(plan[i]))
                return false;
        }

        return true;
    }

    private static void union(int n1, int n2) {
        int n1Parent = find(n1);
        int n2Parent = find(n2);

        if(n1Parent == n2Parent) return;
        if(n1Parent > n2Parent)
            parent[n1Parent] = n2Parent;
        else
            parent[n2Parent] = n1Parent;
    }

    private static int find(int n) {
        if(parent[n] == n)
            return n;
        return parent[n] = find(parent[n]);
    }

    private static void initParent(int n) {
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
    }
}
