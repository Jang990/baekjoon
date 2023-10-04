import java.util.PriorityQueue;

class Solution {
    public static int solution(int[][] routes) {
        int answer = 1;
        PriorityQueue<Road> qu = new PriorityQueue<>((r1, r2) -> {
            if (r1.start < r2.start) {
                return -1;
            } else if (r1.start > r2.start) {
                return 1;
            }

            if (r1.end > r2.end) {
                return -1;
            } else if (r1.end < r2.end) {
                return 1;
            }

            return 0;
        });

        for (int i = 0; i < routes.length; i++) {
            qu.offer(new Road(routes[i][0], routes[i][1]));
        }

        Road range = new Road(-30000, 30000);
        while (!qu.isEmpty()) {
            Road now = qu.poll();
            if (isIn(range, now)) {
                range = new Road(Math.max(range.start, now.start), Math.min(range.end, now.end));
            } else {
                range = now;
                answer++;
            }
        }

        return answer;
    }

    public static boolean isIn(Road base, Road target) {
        if (base.start > target.end || target.start > base.end)
            return false;
        else
            return true;
    }

    static class Road {
        int start, end;

        public Road(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}