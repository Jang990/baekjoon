import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.StringTokenizer;

public class Main {
    static LocalTime prevTime = LocalTime.of(0,0,0);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());

        Team[] teams = new Team[3];
        for (int i = 0; i < 3; i++) {
            teams[i] = new Team(0);
        }

        for (int i = 0; i < n; i++) {
            int winningTeamNumber = getWinnerTeamNumber(teams[1], teams[2]);

            String[] line = br.readLine().split(" ");
            int nowScoringTeam = Integer.valueOf(line[0]);
            LocalTime scoringTime = convertStringToTime(line[1]);

            if (winningTeamNumber != 0) {
                teams[winningTeamNumber].calcWinningTime(scoringTime);
            }

            teams[nowScoringTeam].score(scoringTime);
            prevTime = scoringTime;
        }

        br.close();

        int winningTeamNumber = getWinnerTeamNumber(teams[1], teams[2]);
        if (winningTeamNumber != 0) {
            teams[winningTeamNumber].calcWinningTime(LocalTime.of(0, 48, 0));
        }


        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%02d:%02d",teams[1].getWinningTime().getMinute(), teams[1].getWinningTime().getSecond()) + "\n");
        sb.append(String.format("%02d:%02d",teams[2].getWinningTime().getMinute(), teams[2].getWinningTime().getSecond()) + "\n");
        System.out.println(sb);
    }

    private static int getWinnerTeamNumber(Team team1, Team team2) {
        if (team1.getPoint() == team2.getPoint()) {
            return 0;
        }

        if (team1.getPoint() > team2.getPoint())
            return 1;
        else
            return 2;
    }

    private static LocalTime convertStringToTime(String line) {
        StringTokenizer st = new StringTokenizer(line, ":");
        int minute = Integer.valueOf(st.nextToken());
        int second = Integer.valueOf(st.nextToken());
        return LocalTime.of(0, minute, second);
    }

    static class Team {
        private int point;
        private LocalTime winningTime;
        private LocalTime lastScoreTime;

        public Team(int point) {
            this.point = point;
            this.winningTime = LocalTime.of(0,0,0);
        }

        public void score(LocalTime lastScoreTime) {
            point++;
            this.lastScoreTime = lastScoreTime;
        }

        public void calcWinningTime(LocalTime now) {
            LocalTime diff = now.minusMinutes(prevTime.getMinute()).minusSeconds(prevTime.getSecond());
            winningTime = winningTime.plusMinutes(diff.getMinute()).plusSeconds(diff.getSecond());
            this.lastScoreTime = null;
        }

        public int getPoint() {
            return point;
        }

        public LocalTime getWinningTime() {
            return winningTime;
        }
    }
}
