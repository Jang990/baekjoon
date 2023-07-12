import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int truckCnt = Integer.parseInt(st.nextToken());
        int bridgeLength = Integer.parseInt(st.nextToken());
        int maxLoad = Integer.parseInt(st.nextToken());

        int[] truck = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();
        br.close();

        Queue<Truck> bridge = new LinkedList<>();
        int time = 1;
        int load = 0;
        int nowTruckIdx = 0;
        while (nowTruckIdx < truckCnt) {
            if (bridge.peek() != null && time - bridge.peek().startTime == bridgeLength) {
                load -= bridge.poll().weight;
            }

            if(load + truck[nowTruckIdx] <= maxLoad) {
                load += truck[nowTruckIdx];
                bridge.add(new Truck(time, truck[nowTruckIdx]));
                nowTruckIdx++;
            }

            time++;
        }

        while (!bridge.isEmpty()) {
            time++;
            if (time - bridge.peek().startTime == bridgeLength) {
                bridge.poll();
            }
        }

        System.out.println(time);
    }

    static class Truck {
        int startTime;
        int weight;

        public Truck(int startTime, int weight) {
            this.startTime = startTime;
            this.weight = weight;
        }
    }
}
