import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int[] sanggeun = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).sorted().toArray();
        br.readLine();
        int[] cards = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();
        br.close();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.length; i++) {
            int upper = getUpperBound(sanggeun, cards[i]);
            int lower = getLowerBound(sanggeun, cards[i]);
            sb.append((upper - lower) + " ");
        }

        System.out.println(sb);
    }

    private static int getLowerBound(int[] basedArr, int card) {
        int low = 0;
        int high = basedArr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int midValue = basedArr[mid];

            if (midValue >= card) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private static int getUpperBound(int[] basedArr, int card) {
        int low = 0;
        int high = basedArr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int midValue = basedArr[mid];

            if (midValue <= card) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}
