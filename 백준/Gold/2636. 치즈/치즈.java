import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        br.close();
        // 1 접촉면 2로 세팅
        // 2 그래프내에 2 지우기 + cnt 증가 지운 수 리턴
        // 3. 그래프 내에 1이 없으면 종료. 아니면 1로 루프

        int cheese = 0;
        int hour = 0;

        while (existCheese()) {
            checkSpace();
            cheese = removeCheese();
            hour++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(hour+"\n");
        sb.append(cheese);
        System.out.println(sb.toString());

    }

    private static int removeCheese() {
        int removedCheeseCnt = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (graph[i][j] != -1) {
                    continue;
                }

                graph[i][j] = 0;
                removedCheeseCnt++;
            }
        }

        return removedCheeseCnt;
    }

    static void print() {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void checkSpace() {
        visited = new boolean[graph.length][graph[0].length];

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {

                if (visited[i][j] || graph[i][j] != 0) {
                    continue;
                }

                if (isOutsideIdx(i, j)) {
                    BFS(new Point(j, i));
                }
            }
        }
    }

    private static boolean isOutsideIdx(int i, int j) {
        return i == 0 || j == 0 || i == graph.length - 1 || j == graph[0].length;
    }

    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};
    private static void BFS(Point start) {
        Queue<Point> qu = new LinkedList<>();
        qu.offer(start);
        visited[start.y][start.x] = true;

        while (!qu.isEmpty()) {
            Point now = qu.poll();
            int nextX, nextY;
            for (int i = 0; i < 4; i++) {
                nextX = now.x + dirX[i];
                nextY = now.y + dirY[i];
                if (outOfBound(nextX, nextY) || visited[nextY][nextX]) {
                    continue;
                }

                visited[nextY][nextX] = true;
                if (graph[nextY][nextX] == 0) {
                    qu.offer(new Point(nextX, nextY));
                } else if (graph[nextY][nextX] == 1) {
                    graph[nextY][nextX] = -1;
                }
            }
        }
    }

    private static boolean outOfBound(int nextX, int nextY) {
        return 0 > nextX || nextX >= graph[0].length || 0 > nextY || nextY >= graph.length;
    }

    private static boolean existCheese() {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (graph[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
