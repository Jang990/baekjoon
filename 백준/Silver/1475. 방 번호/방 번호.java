import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        br.close();

        int[] count = new int[9];
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - '0';
            int score = 2;
            if (idx == 6 || idx == 9) {
                idx = 6;
                score = 1;
            }

            count[idx] += score;
        }

        int max = Arrays.stream(count).max().getAsInt();
        if (max % 2 == 1) {
            max++;
        }
        System.out.println(max/2);
    }
}
