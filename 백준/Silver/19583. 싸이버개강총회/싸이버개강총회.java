import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String S = st.nextToken();
        String E = st.nextToken();
        String Q = st.nextToken();

        String line = "";
        Set<String> set = new HashSet<>();

        int result = 0;
        while((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) {
                break;
            }
            String time = st.nextToken();
            String nickname = st.nextToken();
            if (S.compareTo(time) >= 0) {
                set.add(nickname);
            }
            else if(E.compareTo(time) <= 0 && Q.compareTo(time) >= 0) {
                if (set.contains(nickname)) {
                    set.remove(nickname);
                    result++;
                }
            }
        }

        br.close();
        System.out.println(result);
    }
}
