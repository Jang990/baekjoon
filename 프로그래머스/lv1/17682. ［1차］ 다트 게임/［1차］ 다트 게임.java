import java.util.ArrayList;
import java.util.List;

class Solution {
    public static int solution(String dartResult) {
        List<String> list = getResults(dartResult);
        List<Integer> resultList = new ArrayList<>();
        
        for (int i = 0; i < list.size(); i++) {
        	calcResult(resultList, list.get(i).split(" "));
		}
        return resultList.stream().mapToInt(Integer::valueOf).sum();
    }
	
	

	private static void calcResult(List<Integer> resultList, String[] info) {
		int score = Integer.valueOf(info[0]);
        int bonus = 0;
        int result = 0;
        if(info[1].equals("S"))
        	bonus = 1;
        else if(info[1].equals("D"))
        	bonus = 2;
        else if(info[1].equals("T"))
        	bonus = 3;
        
		
    	result = (int)Math.pow(score, bonus);
    	
    	if(info.length == 3) {
    		if(info[2].equals("*")) {
    			if(resultList.size() > 0) {
					int prevResult = resultList.get(resultList.size()-1);
					resultList.remove(resultList.size()-1);
					resultList.add(prevResult*2);
				}
				result *= 2;
    		}
            else if(info[2].equals("#"))
            	result *= -1;
    	}
    	
    	resultList.add(result);
	}

	private static List<String> getResults(String dartResult) {
        String str = dartResult.charAt(0)+"";
        int startIdx = 0;
        List<String> list = new ArrayList<>();
		for (int i = 1; i < dartResult.length(); i++) {
        	char c = dartResult.charAt(i); 
			if('0'<=c && c<='9') {
				if(startIdx == i-1)
					str += c;
				else {
					list.add(str);
					startIdx = i;
					str = c+"";
				}
			}
			else
				str += " "+c;
		}
        list.add(str);
        return list;
	}
}