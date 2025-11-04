import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<TimeLine> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t.start));
        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            pq.offer(new TimeLine(arr[1], arr[2]));
        }
        br.close();

        int maxRoomCnt = 0;
        PriorityQueue<TimeLine> qu = new PriorityQueue<>(Comparator.comparingInt(t -> t.end));
        while (!pq.isEmpty()) {
            TimeLine current = pq.poll();
            qu.offer(current);
            while(!qu.isEmpty() && qu.peek().end <= current.start)
                qu.poll();
            maxRoomCnt = Math.max(maxRoomCnt, qu.size());
        }
        System.out.println(maxRoomCnt);
    }

    static class TimeLine {
        int start , end;

        public TimeLine(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "TimeLine{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
