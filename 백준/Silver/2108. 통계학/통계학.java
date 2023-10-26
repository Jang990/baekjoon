import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.valueOf(br.readLine());
        }
        br.close();

        Arrays.sort(arr);
        int[] answer = new int[4];
        answer[1] = arr[n/2];
        answer[3] = arr[n - 1] - arr[0];

        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            int value = map.getOrDefault(arr[i], 0);
            map.put(arr[i], value +1);
        }
        answer[0] = (int) Math.round((double)sum / n);
        answer[2] = getAnswer3(map);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < answer.length; i++) {
            sb.append(answer[i] + "\n");
        }
        System.out.println(sb);
    }

    private static int getAnswer3(Map<Integer, Integer> map) {
        List<Integer> list = new ArrayList<>();
        int max = 0;
        for (int key : map.keySet()) {
            int value = map.get(key);
            if (value < max)
                continue;

            if (value > max) {
                max = value;
                list.clear();
            }
            list.add(key);
        }

        if (list.size() == 1)
            return list.get(0);

        list.sort(Comparator.naturalOrder());
        return list.get(1);
    }
}
