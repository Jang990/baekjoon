import java.io.*;
import java.util.*;

public class Main {
    static Set<String> set = Set.of("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=");
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        br.close();

        int result = 0;
        for (int i = 0; i < line.length(); i++) {
            if (i < line.length() - 1 && checkWord2(line, i)) {
                i++;
                result++;
                continue;
            }

            if (i < line.length() - 2 && checkWord3(line, i)) {
                i += 2;
                result++;
                continue;
            }
            result++;
        }
        System.out.println(result);
    }

    private static boolean checkWord3(String line, int idx) {
        return set.contains(line.substring(idx, idx + 3));
    }

    private static boolean checkWord2(String line, int idx) {
        return set.contains(line.substring(idx, idx + 2));
    }
}
