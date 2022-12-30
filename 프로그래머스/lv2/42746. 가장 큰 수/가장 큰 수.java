import java.util.Arrays;
import java.util.Comparator;
class Solution {
    public String solution(int[] numbers) {
        Comparator<String> comp = new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return (s2+s1).compareTo(s1+s2);
			}
		};
		
		
		
		StringBuilder sb = new StringBuilder();
		Arrays.stream(numbers).mapToObj(String::valueOf).sorted(comp).forEach(sb::append);
		if(sb.charAt(0) == '0')
			return "0";
		
        return sb.toString();
    }
}