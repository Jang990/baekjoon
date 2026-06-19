import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public static int[] solution(String[] operations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> pqMax = new PriorityQueue<>(Comparator.reverseOrder());

        for (String operation : operations) {
            if (operation.startsWith("I ")) {
                int num = Integer.parseInt(operation.substring(2));
                pq.offer(num);
                pqMax.offer(num);
            }
            else if (operation.equals("D 1"))
                pq.remove(pqMax.poll());
            else if(operation.equals("D -1"))
                pqMax.remove(pq.poll());
        }

        if(pq.isEmpty())
            return new int[] {0, 0};
        else
            return new int[]{pqMax.peek(), pq.peek()};
    }
}