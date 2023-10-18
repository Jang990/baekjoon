import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            String key = br.readLine();
            int value = map.getOrDefault(key, 0);
            max = Math.max(max, value + 1);
            map.put(key, value+1);
        }
        br.close();

        SortedSet<String> set = new TreeSet<>();
        for (String key : map.keySet()) {
            if (map.get(key) == max)
                set.add(key);
        }

        System.out.println(set.first());
    }
}
