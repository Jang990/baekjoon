import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int[] line  = readLine(br);
        while (line[0] != 0) {
            int cnt = line[0];
            StringBuilder num1Sb = new StringBuilder();
            StringBuilder num2Sb = new StringBuilder();
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 1; i <= cnt; i++)
                pq.offer(line[i]);

            for (int i = 0; i < cnt; i++) {
                int current = pq.poll();
                int zeroCnt = 0;
                StringBuilder targetSb;
                if (i % 2 == 0)
                    targetSb = num1Sb;
                else
                    targetSb = num2Sb;

                if (current == 0 && targetSb.length() == 0) {
                    zeroCnt++;
                    int nonZero;
                    while (true) {
                        nonZero = pq.poll();
                        if(nonZero == 0)
                            zeroCnt++;
                        else
                            break;
                    }
                    current = nonZero;
                    for (int j = 0; j < zeroCnt; j++)
                        pq.offer(0);
                }
                targetSb.append(current);
            }

            int sum = Integer.parseInt(num1Sb.toString()) + Integer.parseInt(num2Sb.toString());
            result.append(sum).append("\n");

            line = readLine(br);
        }
        br.close();

        System.out.println(result);
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
