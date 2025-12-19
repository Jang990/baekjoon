import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        int N = (int) arr[0];
        long cuttingCost = arr[1];
        long unitPrice = arr[2];

        int[] trees = new int[N];
        int treeMaxLen = 1;
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(br.readLine());
            treeMaxLen = Math.max(treeMaxLen, trees[i]);
        }

        br.close();

        /*
        만약 잘랐을 때의 이득보다 자르지 않고 놔두는게 더 낫다면 안자를 수도 있음
        ex) 자르기 비용이 10이고 단위당 비용이 4일때 2를 1-1로 나누면 -2 손해임.
        그냥 놔두는게 오히려 좋음
         */
        long result = 0;
        for (int unit = 1; unit <= treeMaxLen; unit++) {
            long totalProfit = 0;
            for (int i = 0; i < trees.length; i++) {
                if(trees[i] < unit)
                    continue;
                int currentUnitCnt = (trees[i] / unit);
                int currentCuttingCnt;
                if (trees[i] % unit == 0) {
                    currentCuttingCnt = (trees[i] / unit - 1);
                } else {
                    currentCuttingCnt = (trees[i] / unit);
                }

                long currentProfit = unit * currentUnitCnt *  unitPrice - currentCuttingCnt * cuttingCost;
                if(currentProfit < 0)
                    continue;
                totalProfit += currentProfit;
            }
            result = Math.max(result, totalProfit);
        }

        System.out.println(result);
    }
}
