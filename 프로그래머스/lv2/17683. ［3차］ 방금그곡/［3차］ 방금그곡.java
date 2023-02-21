import java.util.ArrayList;
import java.util.List;
class Solution {
    public static String solution(String m, String[] musicinfos) {
		String answer = "(None)";
		int time = 0;
		for (String str : musicinfos) {
			String[] info = str.split(",");
			int playTime = calcPlayTime(info[0].split(":"), info[1].split(":"));
			String fullMusic = buildFullMusic(info[3], playTime);
			
			if(m.charAt(m.length()-1) != '#') {
				String remove = m+"#";
				fullMusic = fullMusic.replace(remove, "");
			}
			int idx = fullMusic.indexOf(m);
			if(idx == -1 || time >= playTime)
				continue;
			
			answer = info[2];
			time = playTime;
		}
		
        return answer;
    }
	
	private static String buildFullMusic(String music, int playTime) {
		List<String> sound = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < music.length(); i++) {
			char c = music.charAt(i);
			sb.append(c);
			if(i < music.length()-1 && music.charAt(i+1) == '#') {
				sb.append("#");
				i++;
			}
			
			sound.add(sb.toString());
			sb.delete(0, sb.capacity());
		}
		
		for (int i = 0; i < playTime; i++) {
			sb.append(sound.get(i%sound.size()));
		}
		return sb.toString();
	}

	private static int calcPlayTime(String[] start, String[] end) {
		int hour = Integer.valueOf(end[0]) - Integer.valueOf(start[0]); 
		int minute = Integer.valueOf(end[1]) - Integer.valueOf(start[1]); 
		return hour * 60 + minute;
	}
}