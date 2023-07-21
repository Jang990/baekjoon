import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static Bottle aBottle, bBottle, cBottle;

    static Set<Integer> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int A = Integer.valueOf(arr[0]);
        int B = Integer.valueOf(arr[1]);
        int C = Integer.valueOf(arr[2]);
        br.close();

        set = new HashSet<>();
        aBottle = new Bottle(A, 0);
        bBottle = new Bottle(B, 0);
        cBottle = new Bottle(C, C);
        set.add(C);

        dfs();
        StringBuilder sb = new StringBuilder();
        set.stream().sorted().forEach(n -> sb.append(n + " "));
        System.out.println(sb);
    }

    private static void dfs() {
        int aNow = aBottle.now;
        int bNow = bBottle.now;
        int cNow = cBottle.now;

        process(aBottle, bBottle, aNow);
        process(aBottle, cBottle, aNow);

        process(bBottle, aBottle, bNow);
        process(bBottle, cBottle, bNow);

        process(cBottle, aBottle, cNow);
        process(cBottle, bBottle, cNow);
    }

    private static void process(Bottle bottle1, Bottle bottle2, int now) {
        if (bottle1.isOkToPour(bottle2)) {
            bottle1.pour(bottle2);
            if (aBottle.now == 0) {
                set.add(cBottle.now);
            }
            dfs();
            bottle1.rollback(bottle2, now);
        }
    }

    static class Bottle {
        int max;
        int now;
        Set<Integer> history;

        public Bottle(int max, int now) {
            this.max = max;
            this.now = now;
            history = new HashSet<>();
        }

        public void pour(Bottle target) {
            int remainingSpace = target.getRemainingSpace();

            if (remainingSpace >= this.now) {
                target.now += this.now;
                this.now = 0;
            }
            else {
                target.now = target.max;
                this.now -= remainingSpace;
            }

            target.history.add(target.now);
            this.history.add(this.now);
        }

        public void rollback(Bottle target, int before) {
            int num = before - this.now;
            this.now = before;
            target.now -= num;
        }

        public boolean isOkToPour(Bottle target) {
            if (this.now == 0 || target.isFull()) {
                return false;
            }

            int remainingSpace = target.getRemainingSpace();
            int afterThis;
            int afterTarget;
            if (remainingSpace >= this.now) {
                afterThis = 0;
                afterTarget = target.now + this.now;
            }
            else {
                afterThis = this.now - remainingSpace;
                afterTarget = target.max;
            }

            return !this.history.contains(afterThis) || !target.history.contains(afterTarget);
        }

        private int getRemainingSpace() {
            return this.max - this.now;
        }


        public boolean isFull() {
            return this.now == this.max;
        }
    }
}
