import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public static int solution(String[][] book_time) {
        int answer = 0;
        Room[] room = new Room[book_time.length];
        for (int i = 0; i < book_time.length; i++) {
            String[] arr = book_time[i][0].split(":");
            LocalDateTime startTime = LocalDateTime.of(1, 1, 1, Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
            arr = book_time[i][1].split(":");
            LocalDateTime endTime = LocalDateTime.of(1, 1, 1, Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
            room[i] = new Room(startTime, endTime.plusMinutes(10));
        }

        Arrays.sort(room, (r1, r2) -> {
            if (r1.startTime.isBefore(r2.startTime))
                return -1;
            if (r1.startTime.isAfter(r2.startTime))
                return 1;

            if(r1.endTime.isBefore(r2.endTime))
                return -1;
            if(r1.endTime.isAfter(r2.endTime))
                return 1;

            return 0;
        });

        PriorityQueue<LocalDateTime> qu = new PriorityQueue<>();
        qu.offer(room[0].endTime);

        for (int i = 1; i < room.length; i++) {
            LocalDateTime currentEndTime = qu.peek();
            if (currentEndTime.isBefore(room[i].startTime) || currentEndTime.isEqual(room[i].startTime)) {
                qu.poll();
            }
            qu.offer(room[i].endTime);
        }

        return qu.size();
    }

    static class Room {
        LocalDateTime startTime;
        LocalDateTime endTime;

        public Room(LocalDateTime startTime, LocalDateTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}