import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        Deque<Integer> deque = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            if (str[0].equals("push_front")) {
                int num = Integer.valueOf(str[1]);
                deque.addFirst(num);
            } else if (str[0].equals("push_back")) {
                int num = Integer.valueOf(str[1]);
                deque.addLast(num);
            } else if (str[0].equals("pop_front")) {
                if (deque.isEmpty()) {
                    sb.append(-1);
                } else {
                    sb.append(deque.pollFirst());
                }
                sb.append("\n");
            } else if(str[0].equals("pop_back")){
                if (deque.isEmpty()) {
                    sb.append(-1);
                } else {
                    sb.append(deque.pollLast());
                }
                sb.append("\n");
            } else if(str[0].equals("size")) {
                sb.append(deque.size());
                sb.append("\n");
            } else if(str[0].equals("empty")) {
                if (deque.isEmpty()) {
                    sb.append(1);
                } else {
                    sb.append(0);
                }
                sb.append("\n");
            } else if(str[0].equals("front")) {
                if (deque.isEmpty()) {
                    sb.append(-1);
                } else {
                    sb.append(deque.peekFirst());
                }
                sb.append("\n");
            } else if(str[0].equals("back")) {
                if (deque.isEmpty()) {
                    sb.append(-1);
                } else {
                    sb.append(deque.peekLast());
                }
                sb.append("\n");
            }
        }


        br.close();

        System.out.println(sb);
    }
}
