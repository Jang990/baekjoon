import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        String patternStr = br.readLine();
        patternStr = "^" + patternStr.replace("*", "[a-z]*") + "$";
        Pattern pattern = Pattern.compile(patternStr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            if (pattern.matcher(line).find()) {
                sb.append("DA\n");
            }
            else {
                sb.append("NE\n");
            }
        }
        br.close();

        System.out.println(sb);
    }
}
