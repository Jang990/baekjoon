import java.util.*; 
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> qu1 = new LinkedList<>();
        Queue<Integer> qu2 = new LinkedList<>();
        long sum1 = init(queue1, qu1);
        long sum2 = init(queue2, qu2);

        int result = 0;
        while (true) {
            if(sum1 == sum2)
                break;
            int poll;
            if (sum1 > sum2){
                poll = qu1.poll();
                qu2.offer(poll);
                sum1 -= poll;
                sum2 += poll;
            }
            else {
                poll = qu2.poll();
                qu1.offer(poll);
                sum1 += poll;
                sum2 -= poll;
            }

            result++;
            if(result > queue1.length * 3)
                return -1;
        }

        return result;
    }

    private static long init(int[] queue1, Queue<Integer> qu1) {
        long sum = 0;
        for (int i : queue1) {
            qu1.offer(i);
            sum += i;
        }
        return sum;
    }
}