import java.util.List;
import java.util.stream.Collectors;
class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        
        int[] sArr = s.chars().toArray();
        List<Integer> skipArr = skip.chars().boxed().sorted().collect(Collectors.toList());
        
        int cnt;
        for (int i = 0; i < sArr.length; i++) {
			cnt = 0;
			while(cnt < index) {
				sArr[i]++;
				if(sArr[i] == 123)
					sArr[i] = 97;
				
				if(skipArr.contains(sArr[i]))
					continue;
				else
					cnt++;
			}
			
			answer += (char)sArr[i];
		}
        
        return answer;
    }
}