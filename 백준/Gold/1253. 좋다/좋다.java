import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // 반례를 생각할 때는 음수를 고려하라.
        // 배열에서 두 개의 수로 하나의 수 만들기.(투포인터) -> -1 0 1  -1 + 1 = 0
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).sorted().toArray();
        br.close();

        int result = 0;
        for (int i = 0; i < n; i++) {
            if(isGood(arr, i))
                result++;
        }

        System.out.println(result);
    }

    private static boolean isGood(int[] arr, int currentIdx) {
        int current = arr[currentIdx];
        int left = 0, right = arr.length - 1;

        if(left == currentIdx)
            left++;
        if(right == currentIdx)
            right--;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if(current == sum)
                return true;
            else if (current > sum)
                do { left++; } while(left == currentIdx);
            else
                do { right--; } while(right == currentIdx);
        }

        return false;
    }
}