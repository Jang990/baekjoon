import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
class Solution {
    public static int solution(int[][] scores) {
        Score wanho = new Score(scores[0][0], scores[0][1]);

        List<Score> list = new ArrayList<>();
        for (int[] score : scores) {
            list.add(new Score(score[0], score[1]));
        }
        list.sort((s1, s2) -> s1.compareTo(s2));

        List<Score> awardedList = getAwardedScoreList(list);
        if (!awardedList.contains(wanho)) {
            return -1;
        }

        int wanhoSum = wanho.getWorkAttitude() + wanho.getPeerReviewed();
        return getRank(wanhoSum, awardedList);
    }

    private static List<Score> getAwardedScoreList(List<Score> sortingList) {
        List<Score> awardedList = new ArrayList<>();

        int maxP = 0;
        for (int i = 0; i < sortingList.size(); i++) {
            Score targetScore = sortingList.get(i);
            if (maxP > targetScore.getPeerReviewed()) {
                continue;
            }

            awardedList.add(targetScore);
            maxP = targetScore.getPeerReviewed();
        }
        return awardedList;
    }

    private static int getRank(int wanhoSum, List<Score> awardedList) {
        int[] sums = awardedList.stream()
                .mapToInt(s1 -> s1.getWorkAttitude() + s1.getPeerReviewed())
                .sorted().toArray();

        int prevSum = Integer.MAX_VALUE;
        int rank = 0;
        for (int i = sums.length - 1; i >= 0; i--) {
            int nowSum = sums[i];
            if (prevSum > nowSum) {
                rank++;
            }

            if (nowSum == wanhoSum) {
                return rank;
            }
        }
        return 0;
    }

    static class Score {
        private int workAttitude;
        private int peerReviewed;

        public Score(int workAttitude, int peerReviewed) {
            this.workAttitude = workAttitude;
            this.peerReviewed = peerReviewed;
        }

        public int compareTo(Score anotherScore) {
            if (this.workAttitude > anotherScore.getWorkAttitude())
                return -1;
            if (this.workAttitude < anotherScore.getWorkAttitude())
                return 1;

            if (this.peerReviewed > anotherScore.getPeerReviewed())
                return 1;
            if (this.peerReviewed < anotherScore.getPeerReviewed())
                return -1;

            return 0;
        }

        public int getWorkAttitude() {
            return workAttitude;
        }

        public int getPeerReviewed() {
            return peerReviewed;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Score)) return false;
            Score score = (Score) o;
            return workAttitude == score.getWorkAttitude() && peerReviewed == score.getPeerReviewed();
        }

        @Override
        public int hashCode() {
            return Objects.hash(workAttitude, peerReviewed);
        }
    }
}