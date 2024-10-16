import java.io.*;

public class Main {

    private static City[] cities;
    private static int n;
    private static int time;

    public static void main(String[] args) throws IOException {
        input();

        int[][] dp = new int[n][time + 1];
        for (Edge edge : cities[0].edge) {
            dp[0][edge.time] = Math.max(dp[0][edge.time], edge.fund);
        }

        for (int idx = 0; idx < n - 1; idx++) {
            for (int currentTime = 0; currentTime <= time; currentTime++) {
                if(dp[idx][currentTime] == 0)
                    continue;

                next(dp, idx, currentTime);
            }
        }

        int result = 0;
        for (int sum : dp[n - 1]) {
            result = Math.max(result, sum);
        }
        System.out.println(result);
    }

    private static void next(int[][] dp, int idx, int currentTime) {
        for (Edge edge : cities[idx + 1].edge) {
            int nextTime = currentTime + edge.time;
            if (nextTime > time)
                continue;
            dp[idx +1][nextTime] = Math.max(
                    dp[idx +1][nextTime],
                    dp[idx][currentTime] + edge.fund
            );
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        time = Integer.parseInt(line[1]);
        cities = initCity(br, n);
        br.close();
    }

    private static City[] initCity(BufferedReader br, int N) throws IOException {
        City[] cities = new City[N];
        for (int i = 0; i < N; i++) {
            cities[i] = create(br.readLine().split(" "));
        }
        return cities;
    }

    private static City create(String[] args) {
        return new City(
                new Edge(Integer.parseInt(args[0]), Integer.parseInt(args[1])),
                new Edge(Integer.parseInt(args[2]), Integer.parseInt(args[3]))
        );
    }

    static class City {
        final Edge[] edge;

        public City(Edge e1, Edge e2) {
            edge = new Edge[]{e1, e2};
        }
    }

    static class Edge {
        int time, fund;

        public Edge(int time, int fund) {
            this.time = time;
            this.fund = fund;
        }
    }
}
