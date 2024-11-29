import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();

        int max = Arrays.stream(arr).max().getAsInt();
        System.out.println((arr.length - 1) * max + Arrays.stream(arr).sum() - max);
    }
}
