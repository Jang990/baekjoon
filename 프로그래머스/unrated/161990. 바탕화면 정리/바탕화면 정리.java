class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        answer[0] = Integer.MAX_VALUE;
        answer[1] = Integer.MAX_VALUE;
        
        for (int i = 0; i < wallpaper.length; i++) {
			if(!wallpaper[i].contains("#"))
				continue;
			
			String[] line = wallpaper[i].split("");
			for (int j = 0; j < line.length; j++) {
				if(line[j].equals("."))
					continue;
				
				if(answer[0] > i)
					answer[0] = i;
				if(answer[2] < i)
					answer[2] = i;
				
				if(answer[1] > j)
					answer[1] = j;
				if(answer[3] < j)
					answer[3] = j;
			}
		}
        
        answer[2]++;
        answer[3]++;
        
        return answer;
    }
}