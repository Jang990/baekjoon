import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        String[] ids = new String[n];
        for (int i = 0; i < n; i++) {
            ids[i] = br.readLine();
        }
        br.close();

        int maxLength = ids[0].length();
        int result = maxLength;
        for (int i = 1; i < maxLength; i++) {
            Set<String> set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                String substring = ids[j].substring(maxLength - i, maxLength);
                if(set.contains(substring))
                    break;
                set.add(substring);
            }

            if (set.size() == n) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }
}
