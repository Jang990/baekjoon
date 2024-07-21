import java.time.LocalDateTime;
import java.util.*;

class Solution {
    public static int solution(String[][] book_time) {
        PriorityQueue<Book> pq = new PriorityQueue<>(Comparator.comparing(Book::getEnd));
        for (String[] time : book_time) {
            pq.offer(new Book(convert(time[0]), convert(time[1])));
        }

        List<Book> list = new LinkedList<>();
        while (!pq.isEmpty()) {
            Book now = pq.poll();
            int index, selectedIdx = -1, minDiff = Integer.MAX_VALUE;
            for (index = 0; index < list.size(); index++) {
                if (list.get(index).isOk(now) && list.get(index).diff(now) < minDiff) {
                    selectedIdx = index;
                    minDiff = list.get(index).diff(now);
                }
            }
            if (selectedIdx == -1) {
                list.add(now);
                continue;
            }
            list.remove(selectedIdx);
            list.add(selectedIdx, now);
        }

        return list.size();
    }

    private static LocalDateTime convert(String timeStr) {
        String[] split = timeStr.split(":");
        return LocalDateTime.of(1, 1, 1, Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    static class Book {
        LocalDateTime start, end;

        public Book(LocalDateTime start, LocalDateTime end) {
            this.start = start;
            this.end = end.plusMinutes(10);
        }

        public LocalDateTime getEnd() {
            return end;
        }

        public boolean isOk(Book target) {
            return end.isEqual(target.start) || end.isBefore(target.start);
        }

        public int diff(Book target) {
            LocalDateTime diffTime = target.start.minusHours(end.getHour()).minusMinutes(end.getMinute());
            return diffTime.getHour() * 60 + diffTime.getMinute();
        }
    }
}