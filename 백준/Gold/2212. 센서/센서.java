import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] sencer = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).sorted().toArray();
        br.close();

        int[] distance = new int[N - 1];
        for (int i = 0; i < sencer.length-1; i++) {
            distance[i] = sencer[i + 1] - sencer[i];
        }

        Arrays.sort(distance);
        int result = 0;
        for (int i = 0; i < distance.length-K+1; i++) {
            result += distance[i];
        }

        System.out.println(result);
    }
}
