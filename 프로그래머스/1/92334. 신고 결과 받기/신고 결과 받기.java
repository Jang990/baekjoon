import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Set<String>> logs = new HashMap();
        Map<String, Integer> recivedCount = new HashMap();
        for(int i = 0; i < id_list.length; i++) {
            logs.put(id_list[i], new HashSet<String>());
            recivedCount.put(id_list[i], 0);
        }
        for(int i = 0; i < report.length; i++) {
            String[] log = report[i].split(" ");
            logs.get(log[1]).add(log[0]);
        }
        
        
        for(int i = 0; i < id_list.length; i++) {
            Set<String> reportingUsers = logs.get(id_list[i]);
            int reportedCnt = reportingUsers.size();
            if(reportedCnt >= k) {
                reportingUsers.forEach(user -> recivedCount.put(user, recivedCount.get(user)+1));
            }
        }
        
        for(int i = 0; i < id_list.length; i++) {
            answer[i] = recivedCount.get(id_list[i]);
        }
        
        
        
        
        
        
        return answer;
    }
}