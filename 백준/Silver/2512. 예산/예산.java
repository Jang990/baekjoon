import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int[] budgets = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();
        int maximum = Integer.valueOf(br.readLine());
        br.close();

        long low = 0, high = 1;
        for (int budget : budgets) {
            high = Math.max(high, budget);
        }
        high++;
        
        while (low < high) {
            long mid = (high + low) / 2;
            long total = getTotalBudgets(budgets, mid);

            if (total > maximum)
                high = mid;
            else
                low = mid + 1;
        }

        System.out.println(high-1);
    }

    private static long getTotalBudgets(int[] budgets, long nowBudget) {
        long total = 0;
        for (int budget : budgets) {
            if (nowBudget > budget)
                total += budget;
            else
                total += nowBudget;
        }

        return total;
    }
}
