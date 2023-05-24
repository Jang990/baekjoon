import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] count;
    static boolean[] visited;
    static int[] result;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        count = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();
        visited = new boolean[N];
        result = new int[N];
        Arrays.fill(result, Integer.MIN_VALUE);
        sb = new StringBuilder();

        Rec(0);

        System.out.println(sb.toString());
    }


    private static void Rec(int depth) {
        if (depth == result.length) {
            for (int i : result) {
                sb.append((i+1)+" ");
            }
            return;
        }

        for (int i = 0; i < count.length; i++) {
            if (visited[i]) {
                continue;
            }

            int tallPeopleCount = checkTallCount(depth, i);
            if (count[i] != tallPeopleCount) {
                continue;
            }

            visited[i] = true;
            result[depth] = i;
            Rec(depth+1);
            result[depth] = Integer.MIN_VALUE;
            visited[i] = false;
        }
    }

    private static int checkTallCount(int depth, int now) {
        int cnt = 0;
        for (int i = 0; i < depth; i++) {
            if (result[i] > now) {
                cnt++;
            }
        }
        return cnt;
    }
}
