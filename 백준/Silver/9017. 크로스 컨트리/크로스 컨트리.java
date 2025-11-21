import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (testCase-- > 0) {
            HashMap<Integer, Team> teams = new HashMap<>();
            int timeLimit = Integer.parseInt(br.readLine());
            int[] timeline = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int time = 0; time < timeLimit; time++) {
                int teamId = timeline[time];
                if(!teams.keySet().contains(teamId))
                    teams.put(teamId, new Team(teamId));
                teams.get(teamId).enter(time);
            }

            int currentScore = 1;
            for (int time = 0; time < timeLimit; time++) {
                int teamId = timeline[time];
                if (teams.get(teamId).addScore(currentScore))
                    currentScore++;
            }

            int winningTeamId =  teams.values().stream()
                    .filter(t -> t.getScore() != -1)
                    .sorted((t1, t2) -> {
                        if (t1.getScore() != t2.getScore())
                            return Integer.compare(t1.getScore(), t2.getScore());
                        return Integer.compare(t1.enterTime.get(4), t2.enterTime.get(4));
                    }).findFirst().get().id;
            sb.append(winningTeamId).append("\n");
        }
        br.close();

        System.out.println(sb);
    }

    static class Team {
        int id;
        ArrayList<Integer> enterTime = new ArrayList<>();
        int scoreCnt = 0;
        int score = 0;

        public Team(int id) {
            this.id = id;
        }

        public void enter(int time) {
            enterTime.add(time);
        }

        public int getScore() {
            if(enterTime.size() < 6)
                return -1;
            return score;
        }

        public boolean addScore(int score) {
            if(enterTime.size() < 6)
                return false;
            if (scoreCnt < 4) {
                this.score += score;
                scoreCnt++;
            }
            return true;
        }

        @Override
        public String toString() {
            return "Team{" +
                    "id=" + id +
                    ", enterTime=" + enterTime +
                    ", score=" + getScore() +
                    '}';
        }
    }
}
