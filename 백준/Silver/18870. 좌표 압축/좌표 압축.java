import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();

        int[] sorted = Arrays.stream(arr).distinct().sorted().toArray();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sorted.length; i++) {
            map.put(sorted[i], i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(map.get(i) + " ");
        }
        System.out.println(sb.toString());
    }
}
