import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (testCase-- > 0) {
            int[] input = readLine(br);
            int teamCnt = input[0];
            int problemCnt = input[1];
            int myTeamId = input[2];
            int logCnt = input[3];

            Team[] teams = new Team[teamCnt];
            for (int i = 0; i < teamCnt; i++) {
                teams[i] = new Team(i + 1);
            }

            for (int time = 0; time < logCnt; time++) {
                input = readLine(br);
                int teamId = input[0];
                int problemId = input[1];
                int score = input[2];
                teams[teamId - 1].submit(problemId, score, time);
            }

            Arrays.sort(teams, (t1, t2) -> {
                if(t1.getLastScore() != t2.getLastScore())
                    return t2.getLastScore() - t1.getLastScore();
                if(t1.submitCnt != t2.submitCnt)
                    return t1.submitCnt - t2.submitCnt;
                return t1.lastSubmitTime - t2.lastSubmitTime;
            });

            for (int rank = 1; rank <= teamCnt; rank++) {
                if(myTeamId == teams[rank-1].teamId)
                    sb.append(rank).append("\n");
            }
        }
        br.close();

        System.out.println(sb);
    }

    static class Team {
        Map<Integer, Integer> map = new HashMap<>();
        int submitCnt = 0;
        int lastSubmitTime = 0;
        int teamId;

        public Team(int teamId) {
            this.teamId = teamId;
        }

        public void submit(int problemId, int score, int submitTime) {
            submitCnt++;
            map.put(problemId, Math.max(map.getOrDefault(problemId, 0), score));
            lastSubmitTime = Math.max(lastSubmitTime, submitTime);
        }

        public int getLastScore() {
            return map.values().stream()
                    .mapToInt(Integer::valueOf)
                    .sum();
        }

        @Override
        public String toString() {
            return "Team{" +
                    "map=" + map +
                    ", submitCnt=" + submitCnt +
                    ", lastSubmitTime=" + lastSubmitTime +
                    ", teamId=" + teamId +
                    ", lastScore=" + getLastScore() +
                    '}';
        }
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
