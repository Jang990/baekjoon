import java.time.LocalDateTime;
import java.util.*;
class Solution {
    private static List<String> result;

    public static String[] solution(String[][] plans) {
        result = new ArrayList<>(plans.length);
        Plan[] arr = new Plan[plans.length];
        for (int i = 0; i < plans.length; i++) {
            arr[i] = new Plan(plans[i][0], plans[i][1], plans[i][2]);
        }
        Arrays.sort(arr, Comparator.comparing(Plan::getStart));

        Stack<Plan> stack = new Stack<>();
        LocalDateTime now = arr[0].getStart();
        stack.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            Plan nowPlan = arr[i];

            LocalDateTime prevPlanFinishedTime = null;
            LocalDateTime nextPlanStartTime = nowPlan.getStart();
            if(!stack.isEmpty())
                prevPlanFinishedTime = now.plusMinutes(stack.peek().playtime);

            if (prevPlanFinishedTime != null && prevPlanFinishedTime.isAfter(nextPlanStartTime)) {
                Plan pausedPlan = stack.peek();
                pausedPlan.playtime -= getPassedTime(now, nextPlanStartTime);
            }
            else {
                finishPrevPlans(stack, now, nextPlanStartTime);
            }


            now = nextPlanStartTime;
            stack.push(nowPlan);
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop().name);
        }
        return result.toArray(String[]::new);
    }

    private static void finishPrevPlans(Stack<Plan> stack, LocalDateTime now, LocalDateTime nextPlanStartTime) {
        Plan pop = stack.pop();
        result.add(pop.name);
        now = now.plusMinutes(pop.playtime);

        Plan prevPlan;
        while (!stack.isEmpty()) {
            prevPlan = stack.peek();
            LocalDateTime prevPlanFinishedTime = now.plusMinutes(prevPlan.playtime);
            if (nextPlanStartTime.isBefore(prevPlanFinishedTime)) {
                prevPlan.playtime -= getPassedTime(now, nextPlanStartTime);
                return;
            }
            now = prevPlanFinishedTime;
            result.add(stack.pop().name);
        }
    }

    private static int getPassedTime(LocalDateTime now, LocalDateTime nowPlanStartTime) {
        LocalDateTime passedTime = nowPlanStartTime.minusHours(now.getHour()).minusMinutes(now.getMinute());
        return passedTime.getHour() * 60 + passedTime.getMinute();
    }

    static class Plan {
        String name;
        LocalDateTime start;
        int playtime;

        public Plan(String name, String start, String playtime) {
            this.name = name;
            String[] hm = start.split(":");
            this.start = LocalDateTime.of(1,1,1,Integer.valueOf(hm[0]), Integer.valueOf(hm[1]));
            this.playtime = Integer.valueOf(playtime);
        }

        public LocalDateTime getStart() {
            return start;
        }
    }
}