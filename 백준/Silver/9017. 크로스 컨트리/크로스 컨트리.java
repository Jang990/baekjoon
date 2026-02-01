import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        Team winner = null;
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ArrayList<Integer> teamIds = findTeamId(arr);
            HashMap<Integer, Team> teamIdAndTeam = new HashMap<>();
            for (int teamId : teamIds)
                teamIdAndTeam.put(teamId, new Team(teamId));

            int score = 1;
            for (int teamId : arr) {
                if (!teamIdAndTeam.containsKey(teamId))
                    continue;
                teamIdAndTeam.get(teamId).add(score++);
            }

            winner = null;
            int minSumTop1To4 = Integer.MAX_VALUE;
            int minTop5 = Integer.MAX_VALUE;
            for (Team team : teamIdAndTeam.values()) {
                if (team.sumTop1To4() > minSumTop1To4)
                    continue;
                if (team.sumTop1To4() == minSumTop1To4 && team.getTop5() > minTop5)
                    continue;

                winner = team;
                minSumTop1To4 = team.sumTop1To4();
                minTop5 = team.getTop5();
            }
            sb.append(winner.id).append("\n");
        }
        br.close();

        System.out.println(sb);
    }

    /*
    상위 4명의 주자 점수 합산 계산
    6명이 참가하지 못한 경우 점수 계산에서 제외
    가장 낮은 점수 팀 우승
    동점일 경우 5등이 빨리 들어온 팀 우승
     */

    private static ArrayList<Integer> findTeamId(int[] arr) {
        HashMap<Integer, Integer> teamIdAndCnt = new HashMap<>();
        for (int teamId : arr) {
            teamIdAndCnt.put(teamId, teamIdAndCnt.getOrDefault(teamId, 0) + 1);
        }

        ArrayList<Integer> teamIds = new ArrayList<>();
        for (int teamId : teamIdAndCnt.keySet()) {
            if(teamIdAndCnt.get(teamId) >= 6)
                teamIds.add(teamId);
        }
        return teamIds;
    }


    static class Team {
        private int id;
        private ArrayList<Integer> scores = new ArrayList<>();

        public Team(int id) {
            this.id = id;
        }

        public void add(int score) {
            scores.add(score);
        }

        public int sumTop1To4() {
            return scores.get(0) + scores.get(1) + scores.get(2) + scores.get(3);
        }

        public int getTop5() {
            return scores.get(4);
        }
    }
}
