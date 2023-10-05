import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> qu = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < works.length; i++) {
            qu.offer(works[i]);
        }

        int now = qu.poll();
        int size = 1;
        while (n != 0) {
            if (!qu.isEmpty() && qu.peek() == now) {
                qu.poll();
                size++;
                continue;
            }

            n -= size;
            now--;

            if (n <= 0) {
                for (int i = 0; i < size + n; i++) {
                    answer += (now*now);
                }
                for (int i = 0; i < n * -1; i++) {
                    answer += ((now + 1) * (now + 1));
                }
                break;
            }

            if (now == 0) {
                break;
            }
        }

        while (!qu.isEmpty()) {
            int num = qu.poll();
            answer += (num * num);
        }

        return answer;
    }
}