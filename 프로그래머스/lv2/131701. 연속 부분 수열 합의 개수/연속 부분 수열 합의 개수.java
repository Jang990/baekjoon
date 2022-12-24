import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> set = new HashSet<>();
        
        List<Integer> list = Arrays.stream(elements).boxed().collect(Collectors.toList());
        
        for (int size = 1; size < elements.length; size++) {
			List<Integer> subList = new ArrayList<>(list.subList(0, size));
			int sum = subList.stream().mapToInt(Integer::valueOf).sum();
			set.add(sum);
			
			for (int i = 0; i < elements.length; i++) {
				subList.remove(0);
				subList.add(list.get((i+size)%elements.length));
				
				sum = subList.stream().mapToInt(Integer::valueOf).sum();
				set.add(sum);
			}
		}
        
        set.add(list.stream().mapToInt(Integer::valueOf).sum());
        
        
        return set.size();
    }
}