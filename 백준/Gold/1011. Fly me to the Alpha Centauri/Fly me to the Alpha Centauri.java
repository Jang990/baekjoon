import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.valueOf(br.readLine());
        StringTokenizer st;
        int x, y, len;
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.valueOf(st.nextToken());
            y = Integer.valueOf(st.nextToken());

            len = y - x;
            int result;
            int sqrt = (int) Math.sqrt(len);
            result = sqrt * 2;
            if(sqrt*sqrt == len){
                result--;
            }
            else if (len > sqrt * sqrt + sqrt) {
                result++;
            }

            sb.append(result+"\n");
        }
        System.out.println(sb.toString());

        br.close();
    }
}
