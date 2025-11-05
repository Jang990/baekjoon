import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[] crane;
    private static List<Integer> box;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 50 - 10_000
        // 무거운거 먼저 처리한다. + 가벼운 크레인이 먼저 가져간다.
        // 가벼운 크레인이 무거운 박스를 먼저 집는다.

        int N = Integer.parseInt(br.readLine());
        crane = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        int M = Integer.parseInt(br.readLine());
        box = readBox(br);

        br.close();

        if (crane[crane.length - 1] < box.get(0)) {
            System.out.println(-1);
            return;
        }

        int time = 0;
        while (!box.isEmpty()) {
            time++;
            for (int craneIdx = 0; craneIdx < N; craneIdx++) {
                for (int boxIdx = 0; boxIdx < box.size(); boxIdx++) {
                    if(crane[craneIdx] < box.get(boxIdx))
                        continue;
                    box.remove(boxIdx);
                    break;
                }
            }
        }

        System.out.println(time);
    }

    private static List<Integer> readBox(BufferedReader br) throws IOException {
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        List<Integer> list = new ArrayList<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            list.add(arr[i]);
        }
        return list;
    }
}
