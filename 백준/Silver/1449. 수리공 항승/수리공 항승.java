import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = arr[0];
        int L = arr[1];
        int[] holes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        br.close();

        int rangeMax = -1;
        int result = 0;
        for (int i = 0; i < holes.length; i++) {
            if(rangeMax > holes[i]) {
                continue;
            }
            rangeMax = holes[i]+L;
            result++;
        }

        System.out.println(result);
    }
}
