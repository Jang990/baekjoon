import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Time> times = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String[] strings = br.readLine().split(" ");
            times.add(new Time(
                    Integer.parseInt(strings[0]),
                    Integer.parseInt(strings[1])
            ));
        }
        times.sort((t1, t2) -> {
            if(t1.end != t2.end)
                return Integer.compare(t1.end, t2.end);
            return Integer.compare(t1.start, t2.start);
        });
        br.close();

        int result = 0;
        int currentEnd = 0;
        for (Time time : times) {
            if(time.start < currentEnd)
                continue;
            currentEnd = time.end;
            result++;
        }
        System.out.println(result);
    }

    static class Time {
        int start;
        int end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
