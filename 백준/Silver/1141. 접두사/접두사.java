import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }
        br.close();

        Arrays.sort(arr, (s1, s2) -> Integer.compare(s2.length(), s1.length()));
        Set<String> set = new HashSet<>();
        set.add(arr[0]);

        for (String s : arr) {
            if (isJDSX(set, s)) {
                set.add(s);
            }
        }

        System.out.println(set.size());

    }

    private static boolean isJDSX(Set<String> set, String word) {
        for (String s : set) {
            if (s.indexOf(word) == 0) {
                return false;
            }
        }
        return true;
    }
}
