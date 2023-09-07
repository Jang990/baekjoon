import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String word = br.readLine();
        br.close();

        int result = 0;
        String tempDotString = line.replace(word, ".");
        for (int i = 0; i < tempDotString.length(); i++) {
            if (tempDotString.charAt(i) == '.') {
                result++;
            }
        }

        System.out.println(result);
    }
}
