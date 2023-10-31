import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int[] arrN = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).sorted().toArray();
        br.readLine();
        int[] arrM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        br.close();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrM.length; i++) {
            sb.append(binarySearch(arrN, arrM[i])+"\n");
        }

        System.out.println(sb);
    }

    private static int binarySearch(int[] baseArr, int key) {
        int low = 0;
        int high = baseArr.length-1;
        while (low <= high) {
            int mid = (high + low) / 2;
            int midValue = baseArr[mid];
            // 1 2 3 4 5 6
            if (key == midValue)
                return 1;

            if (key > midValue)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return 0;
    }
}
