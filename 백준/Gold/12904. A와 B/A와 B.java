import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();
        br.close();

        Queue<String> qu = new LinkedList<>();
        qu.offer(S);
        String lastPoll = "";

        while (!qu.isEmpty() && qu.peek().length() != T.length()) {
            String now = qu.poll();
            String type1 = now+"A";
            String type2 = reverseString(now) + "B";

            if (T.contains(type1) || T.contains(reverseString(type1))) {
                qu.offer(type1);
            }
            if (T.contains(type2) || T.contains(reverseString(type2))) {
                qu.offer(type2);
            }
        }

        if (qu.contains(T)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
