import java.util.*;
import java.time.*;
class Solution {
    static Map<String, LocalTime> inTime = new HashMap<>();
    static Map<String, Integer> diffMinute = new HashMap<>();
    public static int[] solution(int[] fees, String[] records) {
        for (String record : records) {
            Log log = convert(record);
            if (log.isIn) {
                inTime.put(log.id, log.time);
            } else {
                diffMinute.put(log.id, diffMinute.getOrDefault(log.id, 0) + calcDiffMinute(inTime.get(log.id), log.time));
                inTime.remove(log.id);
            }
        }

        for (Map.Entry<String, LocalTime> log : inTime.entrySet()) {
            String id = log.getKey();
            LocalTime time = log.getValue();
            diffMinute.put(id, diffMinute.getOrDefault(id, 0) + calcDiffMinute(time, LocalTime.of(23, 59)));
        }

        return diffMinute.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .mapToInt(Map.Entry::getValue)
                .map(minute -> calc(fees, minute))
                .toArray();
    }

    private static int calcDiffMinute(LocalTime start, LocalTime end) {
        LocalTime diffTime = end.minusHours(start.getHour()).minusMinutes(start.getMinute());
        int diffMinute = diffTime.getHour() * 60 + diffTime.getMinute();
        return diffMinute;
    }

    private static int calc(int[] fees, int diffMinute) {
        if(diffMinute < fees[0])
            return fees[1];
        return fees[1] + (int)Math.ceil((double)(diffMinute - fees[0]) / fees[2]) * fees[3];
    }

    private static Log convert(String record) {
        String[] elements = record.split(" ");

        Log result = new Log();
        result.time = convertTime(elements[0]);
        result.id = elements[1];
        if(elements[2].equals("IN"))
            result.isIn = true;

        return result;
    }

    private static LocalTime convertTime(String timeStr) {
        String[] time = timeStr.split(":");
        return LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
    }

    static class Log {
        String id;
        boolean isIn;
        LocalTime time;
    }
}