import java.util.HashMap;
import java.util.Map;
class Solution {
    static final int price = 100;
    static int[] result;
    private static Map<String, Info> relation;
    private static final String center = "-";


    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        result = new int[enroll.length];
        relation = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            relation.put(enroll[i], new Info(i, referral[i]));
        }

        sell(seller, amount);

        return result;
    }

    private static void sell(String[] seller, int[] amount) {
        for (int i = 0; i < seller.length; i++) {
            Info info = relation.get(seller[i]);
            int profit = price * amount[i], div;
            if (info.referral == null) {
                result[info.idx] += profit;
                continue;
            }


            while (true) {
                div = profit / 10;
                result[info.idx] += (profit - div);

                if (info.referral.equals(center)) {
                    break;
                }

                info = relation.get(info.referral);
                profit = div;
                if (div == 0) {
                    break;
                }
            }
        }
    }

    static class Info {
        int idx;
        String referral;

        public Info(int idx, String referral) {
            this.idx = idx;
            this.referral = referral;
        }
    }
}