import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = readLine(br);
        int n = arr[0];
        int stoneLimit = arr[1];
        int[] stones = readLine(br);
        br.close();

        Arrays.sort(stones);
        long stoneCnt = 0;
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += (stones[i] * stoneCnt);
            if(stoneCnt != stoneLimit)
                stoneCnt++;
        }

        System.out.println(result);
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
