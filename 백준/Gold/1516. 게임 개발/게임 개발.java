import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[] time;
    private static int[] enter;
    private static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        time = new int[n + 1];
        enter = new int[n + 1];
        graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int buildingTime = Integer.parseInt(st.nextToken());
            time[i] = buildingTime;
            while (st.hasMoreTokens()) {
                int now = Integer.parseInt(st.nextToken());
                if(now == -1)
                    break;
                graph[now].add(i);
                enter[i]++;
            }
        }
        br.close();

        sort();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(time[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void sort() {
        PriorityQueue<Building> qu = new PriorityQueue<>();
        for (int i = 1; i < enter.length; i++) {
            if (enter[i] == 0) {
                qu.offer(new Building(i, time[i]));
            }
        }

        while (!qu.isEmpty()) {
            Building now = qu.poll();
            for (int next : graph[now.id]) {
                enter[next]--;
                if (enter[next] != 0)
                    continue;

                time[next] += time[now.id];
                qu.offer(new Building(next, time[next]));
            }
        }
    }

    static class Building implements Comparable<Building> {
        int id;
        int time;

        public Building(int id, int time) {
            this.id = id;
            this.time = time;
        }

        @Override
        public int compareTo(Building o) {
            return this.time - o.time;
        }
    }
}
