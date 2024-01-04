import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static int goal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        goal = Integer.parseInt(br.readLine());
        br.close();
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Status> qu = new LinkedList<>();
        qu.offer(new Status());
        // [버퍼][이모티콘]
        boolean[][] visited = new boolean[1001][1001];

        while (!qu.isEmpty()) {
            Status now = qu.poll();
            if (now.emoticon == goal) {
                return now.time;
            }


            if (now.buffer != now.emoticon
                    && !visited[now.emoticon][now.emoticon]) {
                qu.offer(new Status(now.emoticon, now.emoticon, now.time+1));
                visited[now.emoticon][now.emoticon] = true;
            }

            if (now.buffer != 0
                    && now.emoticon + now.buffer <= goal
                    && !visited[now.buffer][now.emoticon + now.buffer]) {
                qu.offer(new Status(now.emoticon + now.buffer, now.buffer, now.time + 1));
                visited[now.buffer][now.emoticon + now.buffer] = true;
            }

            if (now.emoticon > 1
                    && !visited[now.buffer][now.emoticon - 1]) {
                qu.offer(new Status(now.emoticon - 1, now.buffer, now.time + 1));
                visited[now.buffer][now.emoticon - 1] = true;
            }
        }

        return 0;
    }

    static class Status {
        int emoticon;
        int buffer;
        int time;

        public Status() {
            this.emoticon = 1;
            this.buffer = 0;
            this.time = 0;
        }

        public Status(int emoticon, int buffer, int time) {
            this.emoticon = emoticon;
            this.buffer = buffer;
            this.time = time;
        }
    }
}
