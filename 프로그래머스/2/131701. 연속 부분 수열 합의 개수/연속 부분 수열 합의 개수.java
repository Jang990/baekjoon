import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        for (int size = 1; size <= elements.length; size++) {
            int sum = 0;
            for (int i = 0; i < size; i++) {
                sum += elements[i];
            }
            set.add(sum);

            for (int i = 0; i < elements.length; i++) {
                sum -= elements[i];
                int next = (size + i) % elements.length;
                sum += elements[next];
                set.add(sum);
            }
        }

        return set.size();
    }
}