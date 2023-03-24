import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        String s = br.readLine();
        br.close();
        int startIdx;

        for(int i = 0; i < s.length(); i++) {
            int endIdx = s.length()-1;
            for(startIdx = i; startIdx < endIdx; startIdx++) {
                char c1 = s.charAt(startIdx);
                char c2 = s.charAt(endIdx);
                if(c1 == c2)
                    endIdx--;
                else
                    break;
            }

            if(endIdx <= startIdx) {
                answer = i+s.length();
                break;
            }

        }

        System.out.println(answer);
    }
}
