import java.util.*;

class Solution {
    static Map<String, String> graph = new HashMap<>();
    static Map<String, Integer> index = new HashMap<>();
    static int[] result;
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        result = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            index.put(enroll[i], i);
            graph.put(enroll[i], referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            sale(seller[i], amount[i] * 100);
        }
        return result;
    }

    private static void sale(String seller, int price) {
        int resultIndex = index.get(seller);
        if (price <= 1) {
            result[resultIndex] += price;
            return;
        }

        int div = price / 10;
        result[resultIndex] += (price - div);

        String parent = graph.get(seller);
        if(parent.equals("-"))
            return;
        sale(parent, div);
    }
}