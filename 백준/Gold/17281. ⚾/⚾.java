import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static int[][] playerAction;
    private static int inningSize;
    private static int max = Integer.MIN_VALUE;
    private static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inningSize = Integer.parseInt(br.readLine());
        readInning(br);
        br.close();

        selected = new boolean[9];
        selected[0] = true;
        selectOrder(0, new LinkedList<>());

        System.out.println(max);
    }

    private static void selectOrder(int depth, List<Integer> order) {
        if (depth >= 8) {
            order.add(3, 0);
            max = Math.max(max, play(order));
            order.remove(3);
            return;
        }

        for (int i = 0; i < 9; i++) {
            if(selected[i])
                continue;
            order.add(i);
            selected[i] = true;
            selectOrder(depth + 1, order);
            order.remove(order.size() - 1);
            selected[i] = false;
        }
    }

    private static int play(List<Integer> order) {
        int result = 0;
        int inning = 0;
        int orderIdx = 0;
        while (inning < inningSize) {
            int outCount = 0;
            boolean[] base = new boolean[3];
            while (outCount < 3) {
                int currentAction = playerAction[inning][order.get(orderIdx)];

                if (currentAction == 0)
                    outCount++;
                else
                    result += moveBase(base, currentAction);

                orderIdx = (orderIdx + 1) % 9;
            }
            inning++;
        }
        return result;
    }

    private static int moveBase(boolean[] base, int action) {
        int result = 0;
        for (int currentBase = 2; currentBase >= 0; currentBase--) {
            if(!base[currentBase])
                continue;
            base[currentBase] = false;
            int nextBase = currentBase + action;
            if (nextBase >= 3) {
                result++;
            } else {
                base[nextBase] = true;
            }
        }
        if(action == 4)
            result++;
        else
            base[action - 1] = true;

        return result;
    }

    private static void readInning(BufferedReader br) throws IOException {
        playerAction = new int[inningSize][9];
        for (int i = 0; i < inningSize; i++) {
            playerAction[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }
}
