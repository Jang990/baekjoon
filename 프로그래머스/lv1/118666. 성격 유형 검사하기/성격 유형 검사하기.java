import java.util.HashMap;
class Solution {
    public static String solution(String[] survey, int[] choices) {
        String answer = "";
		HashMap<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < choices.length; i++) {
        	int score = choices[i]-4;
        	char choice;
			if(score == 0)
				continue;
			
			if(score > 0) {
				choice = survey[i].charAt(1);
			}
			else {
				choice = survey[i].charAt(0);
			}
			
			map.put(choice, map.getOrDefault(choice, 0) + Math.abs(score));
		}
        
        answer += append(map,"RT");
        answer += append(map,"CF");
        answer += append(map,"JM");
        answer += append(map,"AN");
        
        return answer;
    }

	private static char append(HashMap<Character, Integer> map, String string) {
		if(map.getOrDefault(string.charAt(0), 0) < map.getOrDefault(string.charAt(1), 0))
			return string.charAt(1);
        else
        	return string.charAt(0);
	}
}