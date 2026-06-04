import java.util.*;

class Solution {
    public static int[] solution(int[] progresses, int[] speeds) {
        Queue<Task> tasks = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            tasks.offer(new Task(progresses[i], speeds[i]));
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (!tasks.isEmpty()) {
            Task current = tasks.poll();
            int days = current.getCompletedDays();
            for (Task task : tasks)
                task.doTask(days);

            int cnt = 1;
            while (!tasks.isEmpty() && tasks.peek().isCompleted()) {
                tasks.poll();
                cnt++;
            }
            result.add(cnt);
        }

        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    static class Task {
        int progress, speed;

        public Task(int progress, int speed) {
            this.progress = progress;
            this.speed = speed;
        }

        public int getCompletedDays() {
            int result = (100 - progress) / speed;
            if((100 - progress) % speed == 0)
                return result;
            else
                return result + 1;
        }

        public void doTask(int day) {
            progress += (speed * day);
        }

        public boolean isCompleted() {
            return progress >= 100;
        }
    }
}