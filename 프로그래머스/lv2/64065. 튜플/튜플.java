import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
class Solution {
    public int[] solution(String s) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
		Object[] array = Arrays.stream(s.substring(1, s.length()-1).split("},"))
				.map(st -> st.replace("}", "").replace("{", ""))
				.sorted(Comparator.comparing(String::length)).toArray();
		
		for (Object object : array) {
			Arrays.stream(((String)object).split(",")).mapToInt(Integer::valueOf).forEach(i -> set.add(i));  
		}
		
		return set.stream().mapToInt(Integer::valueOf).toArray();
    }
}