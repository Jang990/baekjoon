import java.util.*;

class Solution {
    HashMap<String, Integer> map = new HashMap<>();
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] arr = convertStringToArray(today);
        for(int i = 0; i < terms.length; i++) {
            String[] term = terms[i].split(" ");
            map.put(term[0], Integer.valueOf(term[1]) * 28);
        }
        
        List<Integer> result = new ArrayList();
        for(int i = 0 ; i < privacies.length; i++) {
            String[] info = privacies[i].split(" ");
            int[] date = convertStringToArray(info[0]);
            int[] diffDate = calculateDifferences(arr, date);
            int day = convertOnlyDay(diffDate);
            if(day >= map.get(info[1]))
                result.add(i+1);
        }
        
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }
    
    int[] convertStringToArray(String dateStr) {
        return Arrays.stream(dateStr.split("\\."))
             .mapToInt(Integer::valueOf).toArray();
    }
    
    int[] calculateDifferences(int[] base, int[] target) {
        int length = 3;
        int[] result = new int[length];
        for(int i = 0; i < length; i++) {
            result[i] =  base[i] - target[i];
        }
        
        return result;
    }
    
    int convertOnlyDay(int[] date) {
        return date[0] * 12 * 28
            + date[1] * 28
            + date[2];
    }
    
}