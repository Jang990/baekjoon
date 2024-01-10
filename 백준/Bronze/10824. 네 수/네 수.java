import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s1 = st.nextToken().concat(st.nextToken());
        String s2 = st.nextToken().concat(st.nextToken());
        br.close();

        boolean rising = false;
        int len = Math.max(s1.length(), s2.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int n1 = getNum(s1, i);
            int n2 = getNum(s2, i);
            int result = n1+n2;
            if(rising)
                result++;
            rising = result >= 10;
            sb.append((result) % 10);
        }

        if(rising)
            sb.append(1);

        System.out.println(sb.reverse());
    }

    private static int getNum(String s, int dig) {
        int resultCharIdx = s.length() -1 - dig;
        if(resultCharIdx < 0)
            return 0;

        return s.charAt(resultCharIdx) - '0';
    }

}
