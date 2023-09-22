import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        List<Lesson> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Lesson(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken())));
        }
        br.close();

        list.sort((l1, l2) -> {
            if(l1.start > l2.start)
                return 1;
            else if(l1.start < l2.start)
                return -1;

            if(l1.end < l2.end)
                return -1;
            else if(l1.end > l2.end)
                return 1;

            return 0;
        });

        PriorityQueue<Integer> qu = new PriorityQueue<>();
        qu.offer(list.get(0).end);

        for (int i = 1; i < list.size(); i++) {
            Lesson lesson = list.get(i);
            if (qu.peek() <= lesson.start) {
                qu.poll();
            }
            qu.offer(lesson.end);
        }

        System.out.println(qu.size());
    }

    static class Lesson {
        int start;
        int end;

        public Lesson(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
