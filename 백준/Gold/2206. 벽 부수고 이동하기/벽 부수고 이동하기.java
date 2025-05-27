import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] cond = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        graph = new int[cond[0]][cond[1]];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        br.close();

        System.out.println(search());
    }

    private static int search() {
        Queue<Info> qu = new LinkedList<>();
        qu.offer(new Info(0, 0, 0));

        int[][][] visited = new int[2][graph.length][graph[0].length];
        visited[0][0][0] = 1;
        visited[1][0][0] = 1;
        int[] dirX = {0, 0, -1, 1};
        int[] dirY = {-1, 1, 0, 0};
        while (!qu.isEmpty()) {
            Info current = qu.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dirX[i];
                int nextY = current.y + dirY[i];

                if(outOfBound(nextX, nextY))
                    continue;

                int nextStep = visited[current.crashed][current.y][current.x] + 1;
                if(visited[current.crashed][nextY][nextX] != 0
                        && visited[current.crashed][nextY][nextX] <= nextStep)
                    continue;

                if (graph[nextY][nextX] == 1 && current.crashed > 0)
                    continue;

                if (graph[nextY][nextX] == 1) {
                    qu.offer(new Info(nextX, nextY, current.crashed + 1));
                    visited[current.crashed + 1][nextY][nextX] = nextStep;
                } else {
                    qu.offer(new Info(nextX, nextY, current.crashed));
                    visited[current.crashed][nextY][nextX] = nextStep;
                }
            }
        }

        boolean visit1 = visited[0][graph.length - 1][graph[0].length - 1] != 0;
        boolean visit2 = visited[1][graph.length - 1][graph[0].length - 1] != 0;
        if(!visit1 && !visit2)
            return -1;

        if(visit1 && !visit2)
            return visited[0][graph.length - 1][graph[0].length - 1];
        else if(!visit1 && visit2)
            return visited[1][graph.length - 1][graph[0].length - 1];
        else
            return Math.min(
                    visited[0][graph.length - 1][graph[0].length - 1],
                    visited[1][graph.length - 1][graph[0].length - 1]
            );
    }

    private static boolean outOfBound(int x, int y) {
        return x < 0 || graph[0].length <= x || y < 0 || graph.length <= y;
    }

    static class Info {
        int x, y, crashed;

        public Info(int x, int y, int crashed) {
            this.x = x;
            this.y = y;
            this.crashed = crashed;
        }
    }
}
