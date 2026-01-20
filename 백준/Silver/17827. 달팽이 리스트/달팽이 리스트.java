import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = readLine(br);
        int nodeCnt = line[0];
        int questionCnt = line[1];
        int cycleNode = line[2];
        int cycleLen = nodeCnt - cycleNode + 1;
        int nonCycleLen = cycleNode - 1;

        int[] nodes = readLine(br);
        StringBuilder sb = new StringBuilder();
        while (questionCnt-- > 0) {
            int n = Integer.parseInt(br.readLine());
            if (nodes.length > n) {
                sb.append(nodes[n]).append("\n");
                continue;
            }

            n -= nonCycleLen;
            sb.append(nodes[(n % cycleLen) + cycleNode - 1]).append("\n");
        }
        br.close();

        System.out.println(sb);
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
