import java.util.ArrayList;
class Solution {
    public int[] solution(int n, long left, long right) {
        ArrayList<Integer> list = new ArrayList<>();
		
		for (long i = left; i <= right; i++) {
			 list.add((int)((i/n > i%n) ? i/n : i%n) + 1);
		}
		
		return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}