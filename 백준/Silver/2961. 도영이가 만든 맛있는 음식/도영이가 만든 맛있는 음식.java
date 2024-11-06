import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] sourTaste;
    static int[] bitterTaste;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        sourTaste = new int[n];
        bitterTaste = new int[n];
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            sourTaste[i] = Integer.parseInt(line[0]);
            bitterTaste[i] = Integer.parseInt(line[1]);
        }
        br.close();

        searchTaste(0, 0, 0);

        System.out.println(min);
    }

    private static void searchTaste(int depth, int sour, int bitter) {
        if (bitter != 0)
            min = Math.min(min, Math.abs(sour - bitter));
        if (depth == sourTaste.length)
            return;

        searchTaste(depth + 1, sour, bitter);
        if(sour == 0)
            searchTaste(depth + 1, sourTaste[depth], bitterTaste[depth] + bitter);
        else
            searchTaste(depth + 1, sourTaste[depth] * sour, bitterTaste[depth] + bitter);

    }
}
