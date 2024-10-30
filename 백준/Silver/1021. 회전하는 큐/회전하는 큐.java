import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static Queue<Integer> wantedNumbers;
    static List<Integer> queue;
    public static void main(String[] args) throws IOException {
        input();

        int queueStartingPoint = 0;
        int result = 0;
        while (!wantedNumbers.isEmpty()) {
            int wantedNum = wantedNumbers.poll();
            int wantedNumIdx = queue.indexOf(wantedNum);
            result += calculateDistanceBetween(queueStartingPoint, wantedNumIdx);
            queueStartingPoint = poll(wantedNumIdx);
        }
        System.out.println(result);
    }

    private static int poll(int wantedNumIdx) {
        queue.remove(wantedNumIdx);
        if(queue.size() == wantedNumIdx)
            return 0;
        return wantedNumIdx;
    }

    private static int calculateDistanceBetween(int idx1, int idx2) {
        if(idx1 == idx2)
            return 0;

        if (idx1 > idx2) {
            int temp = idx1;
            idx1 = idx2;
            idx2 = temp;
        }

        int directWay = idx2 - idx1;
        int reversedWay = idx1 + queue.size() - idx2;
        return Math.min(directWay, reversedWay);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int queueSize = Integer.parseInt(br.readLine().split(" ")[0]);
        queue = IntStream.range(1, queueSize + 1).boxed().collect(Collectors.toList());

        wantedNumbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        br.close();
    }
}
