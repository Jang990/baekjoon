import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            int[] line = readLine(br);
            for (int j = 0; j < line.length; j++) {
                if(line[j] == 0)
                    continue;
                union(i, j + 1);
            }
        }
        int[] plan = readLine(br);
        br.close();

        int root = findParent(plan[0]);
        for (int i = 1; i < plan.length; i++) {
            if (findParent(plan[i]) != root) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    private static int findParent(int n) {
        if (parent[n] == n)
            return n;
        return findParent(parent[n]);
    }

    private static void union(int n1, int n2) {
        int n1Parent = findParent(n1);
        int n2Parent = findParent(n2);

        if (n1Parent > n2Parent) {
            parent[n1Parent] = n2Parent;
        } else {
            parent[n2Parent] = n1Parent;
        }
    }


    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
