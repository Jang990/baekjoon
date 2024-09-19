import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        final int plateCnt = input[0];
        final int sushiTypeCnt = input[1];
        final int storeEventSize = input[2];
        final int coupon = input[3];

        int[] plates = new int[plateCnt];
        for (int i = 0; i < plateCnt; i++) {
            plates[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        Customer customer = new Customer(plates, storeEventSize, coupon);
        int result = customer.sushiType();
        for (int cancelPlate = 0; cancelPlate < plateCnt; cancelPlate++) {
            int eatingPlate = (cancelPlate + storeEventSize) % plateCnt;
            customer.cancel(plates[cancelPlate]);
            customer.eat(plates[eatingPlate]);

            result = Math.max(result, customer.sushiType());
        }

        System.out.println(result);
    }

    static class Customer {
        Map<Integer, Integer> eatingCnt;

        public Customer(int[] plates, int capacity, int coupon) {
            eatingCnt = new HashMap<>();
            for (int i = 0; i < capacity; i++) {
                eat(plates[i]);
            }
            eat(coupon);
        }

        public void eat(int typeOfSushi) {
            eatingCnt.put(typeOfSushi, eatingCnt.getOrDefault(typeOfSushi, 0) + 1);
        }

        public void cancel(int typeOfSushi) {
            if(!eatingCnt.containsKey(typeOfSushi))
                throw new IllegalArgumentException();

            int sushiCnt = eatingCnt.get(typeOfSushi);
            if(sushiCnt == 1)
                eatingCnt.remove(typeOfSushi);
            else
                eatingCnt.put(typeOfSushi, sushiCnt - 1);
        }

        public int sushiType() {
            return eatingCnt.keySet().size();
        }
    }
}
