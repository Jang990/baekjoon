
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] words = br.readLine().split("");
        for (int i = 1; i < n; i++) {
            String[] next = br.readLine().split("");
            for (int j = 0; j < words.length; j++) {
                if(words[j].equals(next[j]))
                    continue;
                words[j] = "?";
            }
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        Arrays.stream(words).forEach(sb::append);
        System.out.println(sb);
    }
}
