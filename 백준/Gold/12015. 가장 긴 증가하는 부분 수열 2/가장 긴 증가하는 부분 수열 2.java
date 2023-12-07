import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();
        br.close();

        List<Integer> list = new ArrayList<>();
        list.add(arr[0]);
        for (int i = 1; i < n; i++) {
            if (arr[i] > list.get(list.size()-1)) {
                list.add(arr[i]);
                continue;
            }

            int idx = getIdx(list, arr[i]);
            list.set(idx, arr[i]);
        }

        System.out.println(list.size());
    }

    private static int getIdx(List<Integer> list, int num) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            int midValue = list.get(mid);
            if(midValue == num)
                return mid;

            if (midValue > num) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
