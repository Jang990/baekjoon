import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] cond = readLine(br);
        graph = new int[cond[2]][cond[1]][cond[0]];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                graph[i][j] = readLine(br);
            }
        }
        br.close();

        System.out.println(process());
    }

    private static int process() {
        Queue<Point> qu = findTomato();

        int[] dirX = {0, 0, 0, 0, 1, -1};
        int[] dirY = {0, 0, 1, -1, 0, 0};
        int[] dirZ = {1, -1, 0, 0, 0, 0};

        int max = Integer.MIN_VALUE;
        while (!qu.isEmpty()) {
            Point current = qu.poll();
            for (int i = 0; i < 6; i++) {
                int nextX = current.x + dirX[i];
                int nextY = current.y + dirY[i];
                int nextZ = current.z + dirZ[i];

                if(outOfBound(nextX,nextY,nextZ)
                        || graph[nextZ][nextY][nextX] == -1)
                    continue;

                if(graph[nextZ][nextY][nextX] != 0
                        && graph[nextZ][nextY][nextX] <= graph[current.z][current.y][current.x] + 1)
                    continue;

                qu.offer(new Point(nextX, nextY, nextZ));
                graph[nextZ][nextY][nextX] = graph[current.z][current.y][current.x] + 1;
                max = Math.max(max, graph[nextZ][nextY][nextX]);
            }
        }

        if(isInvalid())
            return -1;

        if(max == Integer.MIN_VALUE)
            return 0;
        else
            return max - 1;
    }

    private static boolean isInvalid() {
        for (int z = 0; z < graph.length; z++) {
            for (int y = 0; y < graph[0].length; y++) {
                for (int x = 0; x < graph[0][0].length; x++) {
                    if(graph[z][y][x] == 0)
                        return true;
                }
            }
        }
        return false;
    }

    private static boolean outOfBound(int x, int y, int z) {
        return x < 0 || graph[0][0].length <= x
                || y < 0 || graph[0].length <= y
                || z < 0 || graph.length <= z;
    }

    private static Queue<Point> findTomato() {
        Queue<Point> result = new LinkedList<>();
        for (int z = 0; z < graph.length; z++) {
            for (int y = 0; y < graph[0].length; y++) {
                for (int x = 0; x < graph[0][0].length; x++) {
                    if(graph[z][y][x] == 1)
                        result.add(new Point(x, y, z));
                }
            }
        }

        return result;
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = st.countTokens();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = Integer.parseInt(st.nextToken());
        }
        return result;
    }

    static class Point {
        int x, y, z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
