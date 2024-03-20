import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int jewelryCnt = Integer.parseInt(st.nextToken()),
                boxCnt = Integer.parseInt(st.nextToken());

        PriorityQueue<Jewelry> jewelry = new PriorityQueue<>(Comparator.comparing(Jewelry::getWeight));
        while (jewelryCnt-- > 0) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            jewelry.add(new Jewelry(weight, cost));
        }

        PriorityQueue<Integer> box = new PriorityQueue<>();
        while (boxCnt-- > 0) {
            box.offer(Integer.parseInt(br.readLine()));
        }
        br.close();

        long sum = 0;
        PriorityQueue<Integer> pqCost = new PriorityQueue<>(Comparator.reverseOrder());
        while (!box.isEmpty()) {
            int boxSize = box.poll();
            while (!jewelry.isEmpty()) {
                if(jewelry.peek().weight > boxSize)
                    break;
                pqCost.offer(jewelry.poll().getCost());
            }

            if(!pqCost.isEmpty())
                sum += pqCost.poll();
        }

        System.out.println(sum);
    }

    static class Jewelry {
        int weight;
        int cost;

        public Jewelry(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        public int getWeight() {
            return weight;
        }

        public int getCost() {
            return cost;
        }
    }
}
