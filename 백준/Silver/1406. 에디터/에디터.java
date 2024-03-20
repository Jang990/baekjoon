import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String base = br.readLine();
        Stack<Character> stFront = new Stack<>();
        Stack<Character> stBack = new Stack<>();

        for (int i = 0; i < base.length(); i++) {
            stFront.push(base.charAt(i));
        }

        int commandCnt = Integer.parseInt(br.readLine());
        while (commandCnt-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char command = st.nextToken().charAt(0);
            switch (command) {
                case 'P':
                    stFront.push(st.nextToken().charAt(0));
                    break;
                case 'L':
                    if(stFront.isEmpty())
                        break;
                    stBack.push(stFront.pop());
                    break;
                case 'D':
                    if(stBack.isEmpty())
                        break;
                    stFront.push(stBack.pop());
                    break;
                case 'B':
                    if(!stFront.isEmpty()) stFront.pop();
                    break;
            }
        }

        br.close();

        StringBuilder sb = new StringBuilder();
        stFront.stream().forEach(sb::append);
        while (!stBack.isEmpty()) {
            sb.append(stBack.pop());
        }

        System.out.println(sb);
    }
}
