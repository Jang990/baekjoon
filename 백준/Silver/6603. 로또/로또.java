import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String s = br.readLine();
            if (s.equals("0")) {
                break;
            }
            visited = new boolean[50];

            int[] numbers = Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
            Rec(0, numbers, 0);
            sb.append("\n");
        }
        br.close();
        System.out.println(sb);
    }

    private static void Rec(int depth, int[] numbers, int selected) {
        if (depth == 6) {
            for (int i = 1; i < visited.length; i++) {
                if (visited[i]) {
                    sb.append(i + " ");
                }
            }
            sb.append("\n");
        }

        for (int i = selected+1; i < numbers.length; i++) {
            int now = numbers[i];
            if (visited[now]) {
                continue;
            }

            visited[now] = true;
            Rec(depth+1, numbers, i);
            visited[now] = false;
        }
    }
}
