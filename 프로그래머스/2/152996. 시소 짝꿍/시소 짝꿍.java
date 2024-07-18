import java.util.*;

class Solution {
    public static long solution(int[] weights) {
        int[] weightCnt = new int[1001];

        List<Integer> list = new LinkedList<>();
        for (int weight : weights) {
            if (weightCnt[weight] == 0)
                list.add(weight);
            weightCnt[weight]++;
        }

        long result = 0;
        for (int i = 0; i < list.size(); i++) {
            int now = list.get(i);
            if(weightCnt[now] > 1)
                result += ((long) weightCnt[now] * (weightCnt[now] - 1) / 2);

            for (int j = i + 1; j < list.size(); j++) {
                int target = list.get(j);
                if(isOk(now, target))
                    result += ((long) weightCnt[now] * weightCnt[target]);
            }
        }
        return result;
    }

    private static boolean isOk(int now, int target) {
        int[] len = {2, 3, 4};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(now * len[i] == target * len[j])
                    return true;
            }
        }

        return false;
    }
}