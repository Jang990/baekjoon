import java.util.HashMap;
import java.util.Map;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        HashMap<String, Integer> inTime = new HashMap<>();
        HashMap<String, Integer> sumTime = new HashMap<>();
        
        for (String string : records) {
			int timeInfo = Integer.valueOf(string.split(" ")[0].split(":")[0]) * 60 + Integer.valueOf(string.split(" ")[0].split(":")[1]);
			String carInfo = string.split(" ")[1];
			String stateInfo = string.split(" ")[2];
			
			if(stateInfo.equals("IN")) {
				inTime.put(carInfo, timeInfo);
			}
			else if(stateInfo.equals("OUT")) {
				int time = timeInfo - inTime.get(carInfo);
				inTime.remove(carInfo);
				sumTime.put(carInfo, sumTime.getOrDefault(carInfo, 0) + time);
			}
		}
        
        
        if(!inTime.isEmpty()) {
        	for (String key : inTime.keySet()) {
        		int time = (23*60+59) - inTime.get(key);
        		sumTime.put(key, sumTime.getOrDefault(key, 0) + time);
			}
        }
        
        return sumTime.entrySet().stream()
        		.sorted(Map.Entry.comparingByKey())
        		.mapToInt(s -> s.getValue())
        		.map(v -> checkMoney(v, fees))
        		.toArray();
    }
    
    private int checkMoney(int time, int[] fees) {
		if(time <= fees[0]) 
			return fees[1];
		
		return fees[1] + (int)Math.ceil(((double)(time-fees[0]) / fees[2])) * fees[3];
	}
}