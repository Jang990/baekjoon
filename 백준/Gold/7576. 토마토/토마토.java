import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static Queue<Node> qu;
    public static void main(String[] args) throws IOException {
        // 1인 곳을 기준으로 탐색진행 +1을 해가며 탐색 진행
        // 0 이 있다면 실패
        // 최댓값 - 1 을 출력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        qu = new LinkedList<>();
        String[] MN = br.readLine().split(" ");
        int M = Integer.valueOf(MN[0]);
        int N = Integer.valueOf(MN[1]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.valueOf(st.nextToken());
                if(map[i][j] == 1)
                    qu.offer(new Node(j, i));
            }
        }

        BFS();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == -1)
                    continue;

                if(map[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }

                max = Math.max(max, map[i][j]);
            }
        }

        System.out.println(max-1);

        br.close();
    }

    private static void BFS() {
        int[] dirX = {0,0,1,-1};
        int[] dirY = {1,-1,0,0};
        while (!qu.isEmpty()) {
            Node no = qu.poll();
            int day = map[no.y][no.x] + 1;
            for (int i = 0; i < dirX.length; i++) {
                int nowX = no.x + dirX[i];
                int nowY = no.y + dirY[i];

                if(check(nowX, nowY) && map[nowY][nowX] == 0) {
                    qu.offer(new Node(nowX, nowY));
                    map[nowY][nowX] = day;
                }
            }
        }
    }

    private static boolean check(int nowX, int nowY) {
        return 0 <= nowX && nowX < map[0].length && 0 <= nowY && nowY < map.length;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}