import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int[] minLen;
    static List<Integer>[] graph;
    static int maxLen;
    static int startingCityId;

    public static void main(String[] args) throws IOException {
        input();

        bfs(startingCityId);

        StringBuilder sb = new StringBuilder();
        for (int cityId = 1; cityId < minLen.length; cityId++) {
            if(cityId == startingCityId || minLen[cityId] != maxLen)
                continue;
            sb.append(cityId).append("\n");
        }

        if(sb.toString().isEmpty())
            System.out.println(-1);
        else
            System.out.println(sb);
    }

    private static void bfs(int startingCity) {
        Queue<Edge> qu = new LinkedList<>();
        minLen[startingCity] = 0;
        qu.offer(new Edge(0, startingCity));

        while (!qu.isEmpty()) {
            Edge now = qu.poll();
            if(minLen[now.n2] >= maxLen)
                continue;
            int nextLen = minLen[now.n2] + 1;
            for (int next : graph[now.n2]) {
                if(minLen[next] <= nextLen)
                    continue;
                minLen[next] = nextLen;
                qu.offer(new Edge(now.n2, next));
            }
        }

    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] args = br.readLine().split(" ");
        int cityCnt = parse(args[0]);
        int roadCnt = parse(args[1]);
        maxLen = parse(args[2]);
        startingCityId = parse(args[3]);

        initMinLen(cityCnt);
        initGraph(br, cityCnt, roadCnt);

        br.close();
    }

    private static void initMinLen(int cityCnt) {
        minLen = new int[cityCnt + 1];
        Arrays.fill(minLen, Integer.MAX_VALUE);
    }

    private static void initGraph(BufferedReader br, int cityCnt, int roadCnt) throws IOException {
        graph = new List[cityCnt + 1];
        for (int i = 0; i <= cityCnt; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < roadCnt; i++) {
            String[] line = br.readLine().split(" ");
            graph[parse(line[0])].add(parse(line[1]));
        }
    }

    private static int parse(String s) {
        return Integer.parseInt(s);
    }

    static class Edge {
        int n1, n2;

        public Edge(int n1, int n2) {
            this.n1 = n1;
            this.n2 = n2;
        }
    }

}
