import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int[] original = readLine(br);

            int switchIdx = -1;
            int lastMin = -1;
            ArrayList<Integer> elements = new ArrayList<>();
            Arrays.stream(original).sorted().forEach(elements::add);
            for (int j = 0; j < original.length - 1; j++) {
                elements.remove((Integer) original[j]);
                for (int k = elements.size() - 1; k >= 0; k--) {
                    if(original[j] >= elements.get(k))
                        break;
                    switchIdx = j;
                    lastMin = elements.get(k);
                }
            }
            if (switchIdx != -1) {
                switchNum(original, switchIdx, lastIndexOf(original, lastMin));
                Arrays.sort(original, switchIdx + 1, original.length);
            }
            sb.append(toString(original)).append("\n");
        }

        br.close();

        System.out.println(sb);
    }

    private static void switchNum(int[] original, int targetIdx1, int targetIdx2) {
        int temp = original[targetIdx1];
        original[targetIdx1] = original[targetIdx2];
        original[targetIdx2] = temp;
    }

    private static int lastIndexOf(int[] original, int target) {
        for (int i = original.length - 1; i >= 0; i--) {
            if (original[i] == target)
                return i;
        }
        return -1;
    }

    private static String toString(int[] original) {
        StringBuilder sb = new StringBuilder();
        for (int num : original) {
            sb.append((char)(num + 'A'));
        }
        return sb.toString();
    }

    private static int[] readLine(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split("")).mapToInt(s -> s.charAt(0) - 'A').toArray();
    }
}
