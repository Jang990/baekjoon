import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split("-");
        int result = Arrays.stream(parts[0].split("\\+")).mapToInt(Integer::parseInt).sum();

        for (int i = 1; i < parts.length; i++) {
            result -= Arrays.stream(parts[i].split("\\+")).mapToInt(Integer::parseInt).sum();
        }

        System.out.println(result);

        br.close();

    }
}
