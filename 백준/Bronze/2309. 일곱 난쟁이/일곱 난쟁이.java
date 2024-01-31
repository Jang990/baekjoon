import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] height = new int[9];
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            height[i] = Integer.parseInt(br.readLine());
            sum += height[i];
        }
        Arrays.sort(height);
        br.close();

        boolean found = false;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if(found)
                break;
            for (int j = i+1; j < 9; j++) {
                int result = sum - height[i] - height[j];
                if (result == 100) {
                    found = true;
                    set.add(i);
                    set.add(j);
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height.length; i++) {
            if (set.contains(i))
                continue;
            sb.append(height[i]).append("\n");
        }
        System.out.println(sb);
    }
}
