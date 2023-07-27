import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Integer> result = new ArrayList<>();
    static int[] base, target;
    static boolean[] baseVisited;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.valueOf(br.readLine());
        base = new int[N + 1];
        target = new int[N + 1];
        baseVisited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            target[i] = Integer.valueOf(br.readLine());
        }
        br.close();

        for (int i = 1; i <= N; i++) {
            if (result.contains(i)) {
                continue;
            }

            baseVisited[i] = true;
            select(i, target[i]);
            baseVisited[i] = false;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result.size() + "\n");
        result.stream().sorted().forEach(i -> sb.append(i + "\n"));
        System.out.println(sb);
    }

    private static void select(int start, int selectedIdx) {
        if (start == selectedIdx) {
            for (int i = 1; i < baseVisited.length; i++) {
                if (baseVisited[i]) {
                    result.add(i);
                }
            }
            return;
        }


        if (selectedIdx > N || baseVisited[selectedIdx]) {
            return;
        }

        baseVisited[selectedIdx] = true;
        select(start, target[selectedIdx]);
        baseVisited[selectedIdx] = false;
    }
}
