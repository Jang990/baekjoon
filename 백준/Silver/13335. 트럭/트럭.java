import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = readLine(br);
        int truckCnt = input[0];
        int bridgeLen = input[1];
        int bridgeMaxWeight = input[2];

        int[] truckWeights = readLine(br);
        br.close();

        Queue<TruckTimeLine> qu = new LinkedList<>();
        int time = 0;
        int nextTruckIdx = 0;
        int bridgeCurrentWeight = 0;
        while (nextTruckIdx != truckCnt) {
            time++;
            if (!qu.isEmpty() && time - qu.peek().enterTime == bridgeLen) {
                TruckTimeLine exitTruck = qu.poll();
                bridgeCurrentWeight -= exitTruck.weight;
            }

            if (bridgeCurrentWeight + truckWeights[nextTruckIdx] <= bridgeMaxWeight) {
                qu.offer(new TruckTimeLine(truckWeights[nextTruckIdx], time));
                bridgeCurrentWeight += truckWeights[nextTruckIdx];
                nextTruckIdx++;
            }
        }
        System.out.println(time + bridgeLen);
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    static class TruckTimeLine {
        int weight;
        int enterTime;

        public TruckTimeLine(int weight, int enterTime) {
            this.weight = weight;
            this.enterTime = enterTime;
        }
    }
}
