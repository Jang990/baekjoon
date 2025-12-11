import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    static HashMap<String, Integer> map = new HashMap<>();
    static int len = 0;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        len = line.length();
        br.close();

        String[] arr = line.split("");
        for (String s : arr)
            map.put(s, map.getOrDefault(s, 0) + 1);

        rec(0, "");
        System.out.println(result);
    }

    private static void rec(int depth, String prev) {
        if (depth == len) {
            result++;
            return;
        }

        for (String key : map.keySet()) {
            int value = map.get(key);
            if(key.equals(prev) || value == 0)
                continue;

            map.put(key, value - 1);
            rec(depth + 1, key);
            map.put(key, value);
        }
    }
}
