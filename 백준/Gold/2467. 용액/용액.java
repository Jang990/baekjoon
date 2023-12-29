import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();
        br.close();

        int leftIdx = 0;
        int rightIdx = arr.length - 1;
        int resultLeftIdx = 0, resultRightIdx = 0;
        int min = Integer.MAX_VALUE;
        while (leftIdx < rightIdx) {
            int sum = arr[leftIdx] + arr[rightIdx];
            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                resultLeftIdx = leftIdx;
                resultRightIdx = rightIdx;
            }
            if(sum < 0)
                leftIdx++;
            else
                rightIdx--;
        }

        System.out.println(arr[resultLeftIdx] + " " + arr[resultRightIdx]);
    }
}
