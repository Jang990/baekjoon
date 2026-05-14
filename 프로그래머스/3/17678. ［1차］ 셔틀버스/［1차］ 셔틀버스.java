import java.time.LocalDateTime;
import java.util.*;
class Solution {
    public static String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<LocalDateTime> crews = new PriorityQueue<>();
        for (String time : timetable) {
            String[] arr = time.split(":");
            crews.offer(LocalDateTime.of(1, 1, 1, Integer.valueOf(arr[0]), Integer.valueOf(arr[1])));
        }

        LocalDateTime current = LocalDateTime.of(1, 1, 1, 9, 0);
        Queue<LocalDateTime> bus = new LinkedList<>();
        current = current.minusMinutes(t);
        for (int i = 0; i < n; i++) {
            current = current.plusMinutes(t);
            bus.clear();
            for (int j = 0; j < m; j++) {
                if(crews.isEmpty() || current.isBefore(crews.peek()))
                    break;
                bus.offer(crews.poll());
            }
        }

        if(bus.size() != m)
            return buildTimeString(current);
        // 마지막 버스가 가득 찼다면 마지막 사람보다 1분 일찍온다.
        LocalDateTime last = null;
        while (!bus.isEmpty()) {
            last = bus.poll();
        }
        return buildTimeString(last.minusMinutes(1));
    }

    private static String buildTimeString(LocalDateTime time) {
        return String.format("%02d:%02d", time.getHour(), time.getMinute());
    }
}