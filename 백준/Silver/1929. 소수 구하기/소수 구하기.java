import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();

        System.out.println(service(line[0], line[1]));
    }

    private static String service(int n1, int n2) {
        StringBuilder sb = new StringBuilder();
        boolean[] isNotPrime = new boolean[n2 + 1];
        for (int num = 2; num <= n2; num++) {
            if(isNotPrime[num])
                continue;
            if(n1 <= num)
                sb.append(num).append("\n");
            for (int j = 1; num * j <= n2; j++) {
                isNotPrime[num * j] = true;
            }
        }

        return sb.toString();
    }
}
