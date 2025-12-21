import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            String line = br.readLine();
            if(isOk(line))
                sb.append("OK\n");
            else
                sb.append("FAKE\n");
        }
        br.close();

        System.out.println(sb);
    }

    private static boolean isOk(String line) {
        HashMap<Character, Integer> map = new HashMap<>();
        Character expected = null;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if(expected != null && !expected.equals(Character.valueOf(c)))
                return false;
            if (expected != null && expected.equals(Character.valueOf(c))) {
                expected = null;
                continue;
            }

            int cnt = map.getOrDefault(c, 0) + 1;
            if (cnt >= 3) {
                expected = c;
                map.put(c, 0);
            } else {
                map.put(c, cnt);
            }
        }

        if(expected != null)
            return false;

        return true;
    }
}
