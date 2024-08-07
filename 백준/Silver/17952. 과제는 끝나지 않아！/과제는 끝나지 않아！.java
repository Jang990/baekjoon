import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int minute = Integer.parseInt(br.readLine());
        int result = 0;
        Stack<Task> st = new Stack<>();
        for (int i = 0; i < minute; i++) {
            String[] line = br.readLine().split(" ");
            if (line[0].equals("0") && st.isEmpty())
                continue;

            Task task = line[0].equals("0") ? st.pop() : new Task(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
            task.doIt();
            if (task.isFinished())
                result += task.getScore();
            else
                st.push(task);
        }
        br.close();
        System.out.println(result);
    }

    private static void doIt(Stack<Task> st) {
        if(st.isEmpty())
            return;
        Task task = st.pop();
        task.doIt();
    }

    static class Task {
        private int time;
        private int score;

        public Task(int score, int time) {
            this.score = score;
            this.time = time;
        }

        public void doIt() {
            time--;
        }

        public boolean isFinished() {
            return time <= 0;
        }

        public int getScore() {
            return score;
        }
    }
}
