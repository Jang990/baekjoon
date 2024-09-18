import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] visited = new boolean[10_000];
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();



        int goal = findMin(input);
        int index = 0;
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                for (int k = 1; k <= 9; k++) {
                    for (int l = 1; l <= 9; l++) {
                        int next = findMin(new int[]{i, j, k, l});
                        if (visited[next])
                            continue;
                        visited[next] = true;
                        index++;
                        if(next != goal)
                            continue;
                        System.out.println(index);
                        return;
                    }
                }
            }
        }
    }

    public static int findMin(int[] card) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < card.length; i++) {
            result = Math.min(result, convertInt(selectOrder(i, card)));
        }
        return result;
    }

    private static int[] selectOrder(int start, int[] card) {
        int[] result = new int[card.length];
        for (int i = 0; i < card.length; i++) {
            int idx = (i + start) % card.length;
            result[i] = card[idx];
        }
        return result;
    }

    private static int convertInt(int[] card) {
        int result = 0;
        for (int i = 0; i < card.length; i++) {
            result += card[i];
            if(card.length - 1 != i)
                result *= 10;
        }
        return result;
    }
}
