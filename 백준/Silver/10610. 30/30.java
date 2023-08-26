import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        br.close();

        Integer[] arr = new Integer[str.length()];
        int sum = 0;
        boolean isNotFoundZero = true;
        for (int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i) - '0';
            sum += arr[i];
            if(arr[i] == 0)
                isNotFoundZero = false;
        }

        if (isNotFoundZero || sum % 3 != 0) {
            System.out.println(-1);
            return;
        }

        Arrays.sort(arr, Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        System.out.println(sb);
    }
}
