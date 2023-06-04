import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[] line = new int[num];
        for (int i = 0; i < num; i++) {
            line[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        int maxNum = 1;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < num; i++) {
            int nowNum = line[i];
            if (maxNum <= nowNum) {
                while (maxNum != nowNum+1) {
                    st.push(maxNum);
                    sb.append("+\n");
                    maxNum++;
                }
            }


            if(st.peek() != nowNum) {
                System.out.println("NO");
                return;
            }
            else {
                st.pop();
                sb.append("-\n");
            }
        }

        System.out.println(sb.toString());
    }
}
