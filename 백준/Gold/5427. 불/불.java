import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static String[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (testCase-- > 0) {
            String[] line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            graph = new String[y][x];
            for (int i = 0; i < y; i++) {
                graph[i] = br.readLine().split("");
            }

            int result = escape();
            if(result == Integer.MAX_VALUE)
                sb.append("IMPOSSIBLE\n");
            else
                sb.append(result).append("\n");
        }
        br.close();

        System.out.println(sb);
    }

    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};
    private static int escape() {
        Queue<Point> fireQu = find("*");
        Queue<Point> personQu = find("@");
        int[][] fireHistory = new int[graph.length][graph[0].length];
        int[][] personHistory = new int[graph.length][graph[0].length];
        for (Point fireLoc : fireQu)
            fireHistory[fireLoc.y][fireLoc.x] = 1;
        for (Point person : personQu)
            personHistory[person.y][person.x] = 1;

        while (!personQu.isEmpty()) {
            int currentFireSize = fireQu.size();
            for (int i = 0; i < currentFireSize; i++) {
                Point fireLoc = fireQu.poll();
                for (int dirIdx = 0; dirIdx < 4; dirIdx++) {
                    int nextX = fireLoc.x + dirX[dirIdx];
                    int nextY = fireLoc.y + dirY[dirIdx];
                    if(isOutOfBound(nextX, nextY)
                            || graph[nextY][nextX].equals("#")
                            || (fireHistory[nextY][nextX] != 0
                            && fireHistory[nextY][nextX] <= fireHistory[fireLoc.y][fireLoc.x] + 1))
                        continue;
                    fireHistory[nextY][nextX] = fireHistory[fireLoc.y][fireLoc.x] + 1;
                    fireQu.offer(new Point(nextX, nextY));
                }
            }

            int currentPersonSize = personQu.size();
            for (int i = 0; i < currentPersonSize; i++) {
                Point personLoc = personQu.poll();
                for (int dirIdx = 0; dirIdx < 4; dirIdx++) {
                    int nextX = personLoc.x + dirX[dirIdx];
                    int nextY = personLoc.y + dirY[dirIdx];
                    int nextStep = personHistory[personLoc.y][personLoc.x] + 1;
                    if(isOutOfBound(nextX, nextY)
                            || graph[nextY][nextX].equals("#")
                            || (personHistory[nextY][nextX] != 0 && personHistory[nextY][nextX] <= nextStep)
                            || fireHistory[nextY][nextX] != 0)
                        continue;
                    personHistory[nextY][nextX] = nextStep;
                    personQu.offer(new Point(nextX, nextY));
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < graph.length; i++) {
            if(personHistory[i][0] != 0)
                result = Math.min(result, personHistory[i][0]);
            if(personHistory[i][graph[0].length - 1] != 0)
                result = Math.min(result, personHistory[i][graph[0].length - 1]);
        }

        for (int i = 0; i < graph[0].length; i++) {
            if(personHistory[0][i] != 0)
                result = Math.min(result, personHistory[0][i]);
            if(personHistory[graph.length - 1][i] != 0)
                result = Math.min(result, personHistory[graph.length - 1][i]);
        }

        return result;
    }

    private static boolean isOutOfBound(int x, int y) {
        return x < 0 || graph[0].length <= x || y < 0 || graph.length <= y;
    }

    private static LinkedList<Point> find(String target) {
        LinkedList<Point> result = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if(graph[i][j].equals(target))
                    result.add(new Point(j, i));
            }
        }
        return result;
    }
}
