import java.util.Arrays;
import java.util.Comparator;
class Solution {
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings);
		Comparator<String> comp = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				char c1 = o1.charAt(n);
				char c2 = o2.charAt(n);
				
				if(c1 == c2)
					return 0;
				
				if(c1 > c2)
					return 1;
				else
					return -1;
					
			}
		};
		
        return Arrays.stream(strings).sorted(comp).toArray(String[]::new);
    }
}