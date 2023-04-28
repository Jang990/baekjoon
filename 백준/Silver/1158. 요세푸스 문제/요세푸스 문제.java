import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NK = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();
        List<Integer> list = IntStream.range(1, NK[0]+1).boxed().collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int idx = 0;
        while (true) {
            idx += NK[1]-1;
            idx %= list.size();
            sb.append(list.remove(idx));

            if (list.isEmpty()) {
                break;
            } else {
                sb.append(", ");
            }
        }
        sb.append(">");

        System.out.println(sb.toString());
    }
}
