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
            int index;
            for (index = 0; index < list.size(); index++) {
                if (list.get(index).isOk(now)) break;
            }
            if(index != list.size())
                list.remove(index);
            list.add(index, now);
            list.sort(Comparator.comparing(Book::getEnd));
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
    }
}