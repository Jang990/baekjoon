import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (testcase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            CompStr[] arr = new CompStr[n];
            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                arr[i] = new CompStr(s);
            }
            Arrays.sort(arr, Comparator.comparing(CompStr::getContent));
            boolean no = false;
            for (int i = 1; i < arr.length; i++) {
                if (!arr[i].getContent().startsWith(arr[i - 1].getContent()))
                    continue;
                no = true;
                break;
            }

            if(no)
                sb.append("NO\n");
            else
                sb.append("YES\n");
        }
        br.close();

        System.out.println(sb);
    }

    static class CompStr {
        String content;
        int len;

        public CompStr(String content) {
            this.content = content;
            this.len = content.length();
        }

        public String getContent() {
            return content;
        }
    }
}
