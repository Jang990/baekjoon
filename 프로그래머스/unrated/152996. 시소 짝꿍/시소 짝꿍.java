import java.util.Arrays;
import java.util.HashMap;
class Solution {
    /*
    100 vs  ?
    2 3 4
    100 ~ 200
    1:1 3:2 4:3 1:2
     */
    public static long solution(int[] weights) {
        long answer = 0;
        Arrays.sort(weights);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int weight : weights) {
            map.put(weight, map.getOrDefault(weight, 0)+1);
        }

        for (int i = 0; i < weights.length; i++) {
            int now = weights[i];
            map.put(now, map.get(now)-1);
            int[] balanceNumber = createBalanceNumber(now);
            for (int balance : balanceNumber) {
                int check = map.getOrDefault(balance, -1);
                if(check > 0) {
                    answer += check;
                }
            }
        }

        return answer;
    }

    private static int[] createBalanceNumber(int now) {
        int[] check = new int[4];
        check[0] = now;

        if (now % 2 == 0) {
            check[1] = now*3/2;
        }
        else {
            check[1] = -1;
        }

        if(now % 3 == 0) {
            check[2] = now*4/3;
        }
        else {
            check[2] = -1;
        }

        check[3] = now*2;
        return check;
    }
}