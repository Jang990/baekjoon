import java.time.LocalDateTime;
import java.util.PriorityQueue;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        PriorityQueue<LocalDateTime> pq = new PriorityQueue<>();
        for (String time : timetable) {
            String[] arr = time.split(":");
            pq.offer(LocalDateTime.of(1, 1, 1, Integer.valueOf(arr[0]), Integer.valueOf(arr[1])));
        }

        LocalDateTime busTime = LocalDateTime.of(1, 1, 1, 9, 0);
        int inBusCnt = 0;
        LocalDateTime prev = LocalDateTime.of(1, 1, 1, 0, 0);
        for (int i = 0; i < n; i++) {
            for (inBusCnt = 0; inBusCnt < m && !pq.isEmpty(); inBusCnt++) {
                if(pq.peek().isAfter(busTime))
                    break;
                
                LocalDateTime now = pq.poll();
                prev = now;
            }

            busTime = busTime.plusMinutes(t);
        }
        busTime = busTime.minusMinutes(t);

        LocalDateTime myTime;
        if (inBusCnt < m) {
            myTime = busTime;
        }
        else {
            myTime = prev.minusMinutes(1);
        }
        
        return String.format("%02d:%02d", myTime.getHour(), myTime.getMinute());
    }
}