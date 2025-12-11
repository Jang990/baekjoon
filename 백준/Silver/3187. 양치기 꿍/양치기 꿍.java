import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static String[][] graph;
    static boolean[][] visited;

    static int sheepCnt = 0;
    static int wolfCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        graph = new String[Integer.parseInt(line[0])][Integer.parseInt(line[1])];
        visited = new boolean[graph.length][graph[0].length];
        for (int i = 0; i < graph.length; i++)
            graph[i] = br.readLine().split("");
        br.close();

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if(!graph[i][j].equals("#") && !visited[i][j])
                    search(j, i);
            }
        }

        System.out.printf("%d %d\n", sheepCnt, wolfCnt);
    }

    private static void search(int x, int y) {
        int currentWolfCnt = 0;
        int currentSheepCnt = 0;
        Queue<Point> qu = new LinkedList<>();
        visited[y][x] = true;
        qu.offer(new Point(x, y));
        if(graph[y][x].equals("v"))
            currentWolfCnt++;
        if(graph[y][x].equals("k"))
            currentSheepCnt++;

        int[] dirX = {0, 0, 1, -1};
        int[] dirY = {1, -1, 0, 0};
        while (!qu.isEmpty()) {
            Point current = qu.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dirX[i];
                int nextY = current.y + dirY[i];

                if(isOutOfBound(nextX, nextY) || visited[nextY][nextX] || graph[nextY][nextX].equals("#"))
                    continue;

                if(graph[nextY][nextX].equals("v"))
                    currentWolfCnt++;
                if(graph[nextY][nextX].equals("k"))
                    currentSheepCnt++;
                qu.offer(new Point(nextX, nextY));
                visited[nextY][nextX] = true;
            }
        }


        if(currentSheepCnt > currentWolfCnt)
            sheepCnt += currentSheepCnt;
        else
            wolfCnt += currentWolfCnt;
    }

    private static boolean isOutOfBound(int x, int y) {
        return x < 0 || graph[0].length <= x || y < 0 || graph.length <= y;
    }
}
