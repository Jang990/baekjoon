import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
    static Map<Integer, Integer> jobs = new HashMap<>();
    static boolean[] timeline;

    public static void main(String[] args) throws IOException {
        input();
        try {checkWorkingTime();}
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(-1);
            return;
        }
        System.out.println(getResult());
    }

    private static int getResult() {
        for (int time = 0; time < timeline.length; time++) {
            if(!timeline[time])
                continue;
            return time;
        }
        return 0;
    }

    private static void checkWorkingTime() {
        Queue<Integer> qu = new LinkedList<>();
        int idx = timeline.length - 1;
        while (idx >= 0) {
            if(jobs.containsKey(idx))
                qu.offer(jobs.get(idx));
            if (qu.isEmpty()) {
                idx--;
                continue;
            }

            int currentJob = qu.poll();
            timeline[idx] = true;
            idx--;
            for (int i = 0; i < currentJob - 1; i++) {
                if (jobs.containsKey(idx))
                    qu.offer(jobs.get(idx));
                timeline[idx] = true;
                idx--;
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        int maxEndTime = 0;
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            int n1 = Integer.parseInt(line[0]);
            int n2 = Integer.parseInt(line[1]) - 1;
            maxEndTime = Math.max(maxEndTime, n2);
            jobs.put(n2, n1);
        }

        timeline = new boolean[maxEndTime + 1];
        br.close();
    }
}
