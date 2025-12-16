import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static int[][] graph;
    static int[][] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [] line = readLine(br);
        graph = new int[line[0]][line[0]];
        time = new int[line[0]][line[0]];

        for (int i = 0; i < graph.length; i++)
            graph[i] = readLine(br);

        line = readLine(br);
        int second = line[0];
        Point loc = new Point(line[2] - 1, line[1] - 1);
        br.close();

        search();

        if(time[loc.y][loc.x] > second + 1)
            System.out.println(0);
        else
            System.out.println(graph[loc.y][loc.x]);
    }

    private static void search() {
        PriorityQueue<Virus> pq = new PriorityQueue<>(
                Comparator.comparingInt(Virus::getTime)
                        .thenComparing(Virus::getId)
        );

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                int current = graph[i][j];
                if(current == 0)
                    continue;
                pq.offer(new Virus(current, 1, new Point(j, i)));
                time[i][j] = 1;
            }
        }

        int[] dirX = {0, 0, 1, -1};
        int[] dirY = {1, -1, 0, 0};
        while (!pq.isEmpty()) {
            Virus current = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nextTime = current.time + 1;
                int nextX = current.loc.x + dirX[i];
                int nextY = current.loc.y + dirY[i];
                if(isOutOfBound(nextX, nextY) || graph[nextY][nextX] != 0)
                    continue;

                graph[nextY][nextX] = current.id;
                time[nextY][nextX] = nextTime;
                pq.offer(new Virus(current.id, nextTime, new Point(nextX, nextY)));
            }
        }
    }

    private static boolean isOutOfBound(int x, int y) {
        return x < 0 || graph[0].length <= x || y < 0 || graph.length <= y;
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    static class Virus {
        int id, time;
        Point loc;

        public Virus(int id, int time, Point loc) {
            this.id = id;
            this.time = time;
            this.loc = loc;
        }

        public int getId() {
            return id;
        }

        public int getTime() {
            return time;
        }
    }
}
