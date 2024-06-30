import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> wantedIdx = new HashMap<>();
        int[] current = new int[number.length];
        for (int i = 0; i < want.length; i++) {
            wantedIdx.put(want[i], i);
        }

        for (int i = 0; i < 10; i++) {
            Integer idx = wantedIdx.get(discount[i]);
            if(idx == null) continue;
            current[idx]++;
        }
        
        int result = 0;
        if(correct(number, current))
            result++;

        for (int i = 10; i < discount.length; i++) {
            Integer popIdx = wantedIdx.get(discount[i-10]);
            if(popIdx != null)
                current[popIdx]--;

            Integer putIdx = wantedIdx.get(discount[i]);
            if(putIdx != null)
                current[putIdx]++;

            if(correct(number, current))
                result++;
        }

        return result;
    }

    private boolean correct(int[] number, int[] current) {
        for (int i = 0; i < number.length; i++) {
            if(number[i] != current[i])
                return false;
        }
        return true;
    }
}