import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Deque<Character> deque = new LinkedList<>();
        Stack<Boolean> history = new Stack<>();
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            int command = Integer.parseInt(line[0]);
            if (command == 3) {
                if (!history.isEmpty()) {
                    boolean isLast = history.pop();
                    if(isLast)
                        deque.removeLast();
                    else
                        deque.removeFirst();
                }
                continue;
            }

            char block = line[1].charAt(0);
            if (command == 1) {
                deque.addLast(block);
                history.add(true);
            } else {
                deque.addFirst(block);
                history.add(false);
            }
        }
        br.close();

        StringBuilder sb = new StringBuilder();
        for (char c : deque)
            sb.append(c);

        if(sb.length() == 0)
            System.out.println(0);
        else
            System.out.println(sb);
    }
}
