import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (testCase-- > 0) {
            String command = br.readLine();
            LinkedList<Character> list = new LinkedList<>();
            int cursor = 0;
            for (int i = 0; i < command.length(); i++) {
                char now = command.charAt(i);
                if (now == '-') {
                    if (!list.isEmpty() && cursor != 0) {
                        list.remove(--cursor);
                    }
                    continue;
                }
                if (now == '<') {
                    if(cursor != 0)
                        cursor--;
                    continue;
                }
                if (now == '>') {
                    if (cursor < list.size())
                        cursor++;
                    continue;
                }

                list.add(cursor++, now);
            }

            save(sb, list);
        }
        br.close();

        System.out.println(sb);
    }

    private static void save(StringBuilder sb, LinkedList<Character> list) {
        for (char c : list) {
            sb.append(c);
        }
        sb.append("\n");
    }
}
